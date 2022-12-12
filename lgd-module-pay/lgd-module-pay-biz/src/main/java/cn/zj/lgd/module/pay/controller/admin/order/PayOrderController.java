package cn.zj.lgd.module.pay.controller.admin.order;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.zj.lgd.module.pay.controller.admin.order.vo.*;
import cn.zj.lgd.module.pay.convert.order.PayOrderConvert;
import cn.zj.lgd.module.pay.dal.dataobject.merchant.PayAppDO;
import cn.zj.lgd.module.pay.dal.dataobject.merchant.PayMerchantDO;
import cn.zj.lgd.module.pay.dal.dataobject.order.PayOrderDO;
import cn.zj.lgd.module.pay.dal.dataobject.order.PayOrderExtensionDO;
import cn.zj.lgd.module.pay.service.merchant.PayAppService;
import cn.zj.lgd.module.pay.service.merchant.PayMerchantService;
import cn.zj.lgd.module.pay.service.order.PayOrderExtensionService;
import cn.zj.lgd.module.pay.service.order.PayOrderService;
import cn.zj.lgd.framework.common.pojo.CommonResult;
import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.common.util.collection.CollectionUtils;
import cn.zj.lgd.framework.excel.core.util.ExcelUtils;
import cn.zj.lgd.framework.operatelog.core.annotations.OperateLog;
import cn.zj.lgd.framework.pay.core.enums.PayChannelEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.zj.lgd.framework.common.pojo.CommonResult.success;
import static cn.zj.lgd.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Api(tags = "管理后台 - 支付订单")
@RestController
@RequestMapping("/pay/order")
@Validated
public class PayOrderController {

    @Resource
    private PayOrderService orderService;
    @Resource
    private PayOrderExtensionService orderExtensionService;
    @Resource
    private PayMerchantService merchantService;
    @Resource
    private PayAppService appService;

    @GetMapping("/get")
    @ApiOperation("获得支付订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pay:order:query')")
    public CommonResult<PayOrderDetailsRespVO> getOrder(@RequestParam("id") Long id) {
        PayOrderDO order = orderService.getOrder(id);
        if (ObjectUtil.isNull(order)) {
            return success(new PayOrderDetailsRespVO());
        }

        PayMerchantDO merchantDO = merchantService.getMerchant(order.getMerchantId());
        PayAppDO appDO = appService.getApp(order.getAppId());
        PayChannelEnum channelEnum = PayChannelEnum.getByCode(order.getChannelCode());

        // TODO @aquan：文案，都是前端 format；
        PayOrderDetailsRespVO respVO = PayOrderConvert.INSTANCE.orderDetailConvert(order);
        respVO.setMerchantName(ObjectUtil.isNotNull(merchantDO) ? merchantDO.getName() : "未知商户");
        respVO.setAppName(ObjectUtil.isNotNull(appDO) ? appDO.getName() : "未知应用");
        respVO.setChannelCodeName(ObjectUtil.isNotNull(channelEnum) ? channelEnum.getName() : "未知渠道");

        PayOrderExtensionDO extensionDO = orderExtensionService.getOrderExtension(order.getSuccessExtensionId());
        if (ObjectUtil.isNotNull(extensionDO)) {
            respVO.setPayOrderExtension(PayOrderConvert.INSTANCE.orderDetailExtensionConvert(extensionDO));
        }

        return success(respVO);
    }

    @GetMapping("/page")
    @ApiOperation("获得支付订单分页")
    @PreAuthorize("@ss.hasPermission('pay:order:query')")
    public CommonResult<PageResult<PayOrderPageItemRespVO>> getOrderPage(@Valid PayOrderPageReqVO pageVO) {
        PageResult<PayOrderDO> pageResult = orderService.getOrderPage(pageVO);
        if (CollectionUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal()));
        }

        // 处理商户ID数据
        Map<Long, PayMerchantDO> merchantMap = merchantService.getMerchantMap(
                CollectionUtils.convertList(pageResult.getList(), PayOrderDO::getMerchantId));
        // 处理应用ID数据
        Map<Long, PayAppDO> appMap = appService.getAppMap(
                CollectionUtils.convertList(pageResult.getList(), PayOrderDO::getAppId));

        List<PayOrderPageItemRespVO> pageList = new ArrayList<>(pageResult.getList().size());
        pageResult.getList().forEach(c -> {
            PayMerchantDO merchantDO = merchantMap.get(c.getMerchantId());
            PayAppDO appDO = appMap.get(c.getAppId());
            PayChannelEnum channelEnum = PayChannelEnum.getByCode(c.getChannelCode());

            PayOrderPageItemRespVO orderItem = PayOrderConvert.INSTANCE.pageConvertItemPage(c);
            orderItem.setMerchantName(ObjectUtil.isNotNull(merchantDO) ? merchantDO.getName() : "未知商户");
            orderItem.setAppName(ObjectUtil.isNotNull(appDO) ? appDO.getName() : "未知应用");
            orderItem.setChannelCodeName(ObjectUtil.isNotNull(channelEnum) ? channelEnum.getName() : "未知渠道");
            pageList.add(orderItem);
        });
        return success(new PageResult<>(pageList, pageResult.getTotal()));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出支付订单Excel")
    @PreAuthorize("@ss.hasPermission('pay:order:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid PayOrderExportReqVO exportReqVO,
            HttpServletResponse response) throws IOException {

        List<PayOrderDO> list = orderService.getOrderList(exportReqVO);
        if (CollectionUtil.isEmpty(list)) {
            ExcelUtils.write(response, "支付订单.xls", "数据",
                    PayOrderExcelVO.class, new ArrayList<>());
        }

        // 处理商户ID数据
        Map<Long, PayMerchantDO> merchantMap = merchantService.getMerchantMap(
                CollectionUtils.convertList(list, PayOrderDO::getMerchantId));
        // 处理应用ID数据
        Map<Long, PayAppDO> appMap = appService.getAppMap(
                CollectionUtils.convertList(list, PayOrderDO::getAppId));
        // 处理扩展订单数据
        Map<Long, PayOrderExtensionDO> orderExtensionMap = orderExtensionService
                .getOrderExtensionMap(CollectionUtils.convertList(list, PayOrderDO::getSuccessExtensionId));

        List<PayOrderExcelVO> excelDatum = new ArrayList<>(list.size());
        list.forEach(c -> {
            PayMerchantDO merchantDO = merchantMap.get(c.getMerchantId());
            PayAppDO appDO = appMap.get(c.getAppId());
            PayChannelEnum channelEnum = PayChannelEnum.getByCode(c.getChannelCode());
            PayOrderExtensionDO orderExtensionDO = orderExtensionMap.get(c.getSuccessExtensionId());

            PayOrderExcelVO excelItem = PayOrderConvert.INSTANCE.excelConvert(c);
            excelItem.setMerchantName(ObjectUtil.isNotNull(merchantDO) ? merchantDO.getName() : "未知商户");
            excelItem.setAppName(ObjectUtil.isNotNull(appDO) ? appDO.getName() : "未知应用");
            excelItem.setChannelCodeName(ObjectUtil.isNotNull(channelEnum) ? channelEnum.getName() : "未知渠道");
            excelItem.setNo(ObjectUtil.isNotNull(orderExtensionDO) ? orderExtensionDO.getNo() : "");
            excelDatum.add(excelItem);
        });

        // 导出 Excel
        ExcelUtils.write(response, "支付订单.xls", "数据", PayOrderExcelVO.class, excelDatum);
    }

}

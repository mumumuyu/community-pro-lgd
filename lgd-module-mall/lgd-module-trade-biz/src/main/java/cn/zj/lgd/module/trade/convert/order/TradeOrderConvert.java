package cn.zj.lgd.module.trade.convert.order;

import cn.zj.lgd.framework.common.util.collection.CollectionUtils;
import cn.zj.lgd.module.member.api.address.dto.AddressRespDTO;
import cn.zj.lgd.module.pay.api.order.PayOrderInfoCreateReqDTO;
import cn.zj.lgd.module.product.api.sku.dto.ProductSkuRespDTO;
import cn.zj.lgd.module.product.api.sku.dto.ProductSkuUpdateStockReqDTO;
import cn.zj.lgd.module.product.api.spu.dto.ProductSpuRespDTO;
import cn.zj.lgd.module.promotion.api.price.dto.PriceCalculateReqDTO;
import cn.zj.lgd.module.promotion.api.price.dto.PriceCalculateRespDTO;
import cn.zj.lgd.module.trade.controller.app.order.vo.AppTradeOrderCreateReqVO;
import cn.zj.lgd.module.trade.dal.dataobject.order.TradeOrderDO;
import cn.zj.lgd.module.trade.dal.dataobject.order.TradeOrderItemDO;
import cn.zj.lgd.module.trade.enums.order.TradeOrderItemRefundStatusEnum;
import cn.zj.lgd.module.trade.framework.order.config.TradeOrderProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static cn.zj.lgd.framework.common.util.collection.CollectionUtils.convertMap;
import static cn.zj.lgd.framework.common.util.date.LocalDateTimeUtils.addTime;

@Mapper
public interface TradeOrderConvert {

    TradeOrderConvert INSTANCE = Mappers.getMapper(TradeOrderConvert.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "createReqVO.couponId", target = "couponId"),
            @Mapping(target = "remark", ignore = true),
            @Mapping(source = "createReqVO.remark", target = "userRemark"),
            @Mapping(source = "address.name", target = "receiverName"),
            @Mapping(source = "address.mobile", target = "receiverMobile"),
            @Mapping(source = "address.areaId", target = "receiverAreaId"),
            @Mapping(source = "address.postCode", target = "receiverPostCode"),
            @Mapping(source = "address.detailAddress", target = "receiverDetailAddress"),
    })
    TradeOrderDO convert(Long userId, String userIp, AppTradeOrderCreateReqVO createReqVO,
                         PriceCalculateRespDTO.Order order, AddressRespDTO address);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "sku.spuId", target = "spuId"),
    })
    TradeOrderItemDO convert(PriceCalculateRespDTO.OrderItem orderItem, ProductSkuRespDTO sku);

    default List<TradeOrderItemDO> convertList(TradeOrderDO tradeOrderDO,
                                               List<PriceCalculateRespDTO.OrderItem> orderItems, List<ProductSkuRespDTO> skus) {
        Map<Long, ProductSkuRespDTO> skuMap = convertMap(skus, ProductSkuRespDTO::getId);
        return CollectionUtils.convertList(orderItems, orderItem -> {
            TradeOrderItemDO tradeOrderItemDO = convert(orderItem, skuMap.get(orderItem.getSkuId()));
            tradeOrderItemDO.setOrderId(tradeOrderDO.getId());
            tradeOrderItemDO.setUserId(tradeOrderDO.getUserId());
            tradeOrderItemDO.setRefundStatus(TradeOrderItemRefundStatusEnum.NONE.getStatus()).setRefundTotal(0); // 退款信息
//            tradeOrderItemDO.setCommented(false);
            return tradeOrderItemDO;
        });
    }

    @Mapping(source = "userId" , target = "userId")
    PriceCalculateReqDTO convert(AppTradeOrderCreateReqVO createReqVO, Long userId);

    @Mappings({
            @Mapping(source = "skuId", target = "id"),
            @Mapping(source = "count", target = "incrCount"),
    })
    ProductSkuUpdateStockReqDTO.Item convert(TradeOrderItemDO bean);
    List<ProductSkuUpdateStockReqDTO.Item> convertList(List<TradeOrderItemDO> list);

    default PayOrderInfoCreateReqDTO convert(TradeOrderDO tradeOrderDO, List<TradeOrderItemDO> tradeOrderItemDOs,
                                             List<ProductSpuRespDTO> spus, TradeOrderProperties tradeOrderProperties) {
        PayOrderInfoCreateReqDTO createReqDTO = new PayOrderInfoCreateReqDTO()
                .setAppId(tradeOrderProperties.getAppId()).setUserIp(tradeOrderDO.getUserIp());
        // 商户相关字段
        createReqDTO.setMerchantOrderId(String.valueOf(tradeOrderDO.getId()));
        String subject = spus.get(0).getName();
        if (spus.size() > 1) {
            subject += " 等多件";
        }
        createReqDTO.setSubject(subject);
        // 订单相关字段
        createReqDTO.setAmount(tradeOrderDO.getPayPrice()).setExpireTime(addTime(tradeOrderProperties.getExpireTime()));
        return createReqDTO;
    }

}

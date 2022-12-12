package cn.zj.lgd.module.promotion.controller.app.banner;

import cn.zj.lgd.framework.common.pojo.CommonResult;
import cn.zj.lgd.module.promotion.controller.admin.banner.vo.BannerRespVO;
import cn.zj.lgd.module.promotion.convert.banner.BannerConvert;
import cn.zj.lgd.module.promotion.dal.dataobject.banner.BannerDO;
import cn.zj.lgd.module.promotion.service.banner.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cn.zj.lgd.framework.common.pojo.CommonResult.success;

/**
 * @author: XIA
 */
@RestController
@RequestMapping("/market/banner")
@Api(tags = "用户APP- 首页Banner")
@Validated
public class AppBannerController {

    @Resource
    private BannerService bannerService;

    // TODO @xia：新建一个 AppBannerRespVO，只返回必要的字段。status 要过滤下。然后 sort 下结果
    @GetMapping("/list")
    @ApiOperation("获得banner列表")
    @PreAuthorize("@ss.hasPermission('market:banner:query')")
    public CommonResult<List<BannerRespVO>> getBannerList() {
        List<BannerDO> list = bannerService.getBannerList();
        return success(BannerConvert.INSTANCE.convertList(list));
    }

}

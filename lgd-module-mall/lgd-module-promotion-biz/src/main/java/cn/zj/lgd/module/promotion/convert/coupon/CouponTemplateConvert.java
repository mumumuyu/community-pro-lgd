package cn.zj.lgd.module.promotion.convert.coupon;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.promotion.controller.admin.coupon.vo.template.CouponTemplateCreateReqVO;
import cn.zj.lgd.module.promotion.controller.admin.coupon.vo.template.CouponTemplateRespVO;
import cn.zj.lgd.module.promotion.controller.admin.coupon.vo.template.CouponTemplateUpdateReqVO;
import cn.zj.lgd.module.promotion.dal.dataobject.coupon.CouponTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 优惠劵模板 Convert
 *
 *
 */
@Mapper
public interface CouponTemplateConvert {

    CouponTemplateConvert INSTANCE = Mappers.getMapper(CouponTemplateConvert.class);

    CouponTemplateDO convert(CouponTemplateCreateReqVO bean);

    CouponTemplateDO convert(CouponTemplateUpdateReqVO bean);

    CouponTemplateRespVO convert(CouponTemplateDO bean);

    PageResult<CouponTemplateRespVO> convertPage(PageResult<CouponTemplateDO> page);

}

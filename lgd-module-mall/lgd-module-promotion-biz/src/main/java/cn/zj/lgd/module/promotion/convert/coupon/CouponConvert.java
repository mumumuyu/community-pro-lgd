package cn.zj.lgd.module.promotion.convert.coupon;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.promotion.controller.admin.coupon.vo.coupon.CouponPageItemRespVO;
import cn.zj.lgd.module.promotion.dal.dataobject.coupon.CouponDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 优惠劵 Convert
 *
 *
 */
@Mapper
public interface CouponConvert {

    CouponConvert INSTANCE = Mappers.getMapper(CouponConvert.class);

    PageResult<CouponPageItemRespVO> convertPage(PageResult<CouponDO> page);

}

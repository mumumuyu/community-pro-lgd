package cn.zj.lgd.module.promotion.api.coupon;

import cn.zj.lgd.module.promotion.api.coupon.dto.CouponUseReqDTO;

import javax.validation.Valid;

/**
 * 优惠劵 API 接口
 *
 *
 */
public interface CouponApi {

    /**
     * 使用优惠劵
     *
     * @param useReqDTO 使用请求
     */
    void useCoupon(@Valid CouponUseReqDTO useReqDTO);

}

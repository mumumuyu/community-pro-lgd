package cn.zj.lgd.module.promotion.api.coupon;


import cn.zj.lgd.module.promotion.api.coupon.dto.CouponUseReqDTO;
import cn.zj.lgd.module.promotion.service.coupon.CouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 优惠劵 API 实现类
 *
 * 
 */
@Service
public class CouponApiImpl implements CouponApi {

    @Resource
    private CouponService couponService;

    @Override
    public void useCoupon(CouponUseReqDTO useReqDTO) {
        couponService.useCoupon(useReqDTO.getId(), useReqDTO.getUserId(),
                useReqDTO.getOrderId());
    }

}

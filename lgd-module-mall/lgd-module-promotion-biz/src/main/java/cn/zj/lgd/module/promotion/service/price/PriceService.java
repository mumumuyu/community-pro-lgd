package cn.zj.lgd.module.promotion.service.price;

import cn.zj.lgd.module.promotion.api.price.dto.CouponMeetRespDTO;
import cn.zj.lgd.module.promotion.api.price.dto.PriceCalculateReqDTO;
import cn.zj.lgd.module.promotion.api.price.dto.PriceCalculateRespDTO;

import java.util.List;

/**
 * 价格计算 Service 接口
 *
 *
 */
public interface PriceService {

    /**
     * 计算商品的价格
     *
     * @param calculateReqDTO 价格请求
     * @return 价格响应
     */
    PriceCalculateRespDTO calculatePrice(PriceCalculateReqDTO calculateReqDTO);

    /**
     * 获得优惠劵的匹配信息列表
     *
     * @param calculateReqDTO 价格请求
     * @return 价格响应
     */
    List<CouponMeetRespDTO> getMeetCouponList(PriceCalculateReqDTO calculateReqDTO);

}

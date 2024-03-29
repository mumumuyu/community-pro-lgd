package cn.zj.lgd.module.promotion.controller.admin.discount.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ApiModel("管理后台 - 限时折扣活动的详细 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiscountActivityDetailRespVO extends DiscountActivityRespVO {

    /**
     * 商品列表
     */
    private List<Product> products;

}

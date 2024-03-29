package cn.zj.lgd.module.promotion.controller.admin.discount.vo;

import cn.hutool.core.util.ObjectUtil;
import cn.zj.lgd.framework.common.validation.InEnum;
import cn.zj.lgd.module.promotion.enums.common.PromotionDiscountTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static cn.zj.lgd.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 限时折扣活动 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class DiscountActivityBaseVO {

    @ApiModelProperty(value = "活动标题", required = true, example = "一个标题")
    @NotNull(message = "活动标题不能为空")
    private String name;

    @ApiModelProperty(value = "开始时间", required = true)
    @NotNull(message = "开始时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    @NotNull(message = "结束时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

    @ApiModelProperty(value = "备注", example = "我是备注")
    private String remark;

    @ApiModel("商品")
    @Data
    public static class Product {

        @ApiModelProperty(value = "商品 SPU 编号", required = true, example = "1")
        @NotNull(message = "商品 SPU 编号不能为空")
        private Long spuId;

        @ApiModelProperty(value = "商品 SKU 编号", required = true, example = "1")
        @NotNull(message = "商品 SKU 编号不能为空")
        private Long skuId;

        @ApiModelProperty(value = "优惠类型", required = true, example = "1", notes = "参见 PromotionDiscountTypeEnum 枚举")
        @NotNull(message = "优惠类型不能为空")
        @InEnum(PromotionDiscountTypeEnum.class)
        private Integer discountType;

        @ApiModelProperty(value = "折扣百分比", example = "80", notes = "例如说，80% 为 80")
        private Integer discountPercent;

        @ApiModelProperty(value = "优惠金额", example = "10", notes = "单位：分")
        @Min(value = 0, message = "优惠金额需要大于等于 0")
        private Integer discountPrice;

        @AssertTrue(message = "折扣百分比需要大于等于 1，小于等于 99")
        @JsonIgnore
        public boolean isDiscountPercentValid() {
            return ObjectUtil.notEqual(discountType, PromotionDiscountTypeEnum.PERCENT.getType())
                    || (discountPercent != null && discountPercent >= 1 && discountPercent<= 99);
        }

        @AssertTrue(message = "优惠金额不能为空")
        @JsonIgnore
        public boolean isDiscountPriceValid() {
            return ObjectUtil.notEqual(discountType, PromotionDiscountTypeEnum.PRICE.getType())
                    || discountPrice != null;
        }

    }
}

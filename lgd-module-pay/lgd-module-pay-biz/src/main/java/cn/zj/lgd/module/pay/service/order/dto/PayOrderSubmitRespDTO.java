package cn.zj.lgd.module.pay.service.order.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 支付单提交 Response DTO
 */
@Data
public class PayOrderSubmitRespDTO implements Serializable {

    /**
     * 支付拓展单的编号
     */
    private Long extensionId;

    /**
     * 调用支付渠道的响应结果
     */
    private Object invokeResponse;

}

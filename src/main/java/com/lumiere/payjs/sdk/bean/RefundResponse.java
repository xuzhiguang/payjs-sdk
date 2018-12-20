package com.lumiere.payjs.sdk.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author xuzhiguang
 * @date 2018/12/20
 */
@Data
@ToString(callSuper = true)
public class RefundResponse extends PayJsResponse {

    /**
     * 返回信息
     */
    private String returnMsg;

    /**
     * payjs端订单号
     */
    private String payjsOrderId;

    /**
     * 	用户侧订单号
     */
    private String outTradeNo;

    /**
     * 微信侧支付订单号
     */
    private String transactionId;

    /**
     * 数据签名
     */
    private String sign;

}

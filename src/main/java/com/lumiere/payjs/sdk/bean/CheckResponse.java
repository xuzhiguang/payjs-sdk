package com.lumiere.payjs.sdk.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
@Data
@ToString(callSuper = true)
public class CheckResponse extends PayJsResponse {

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 用户端订单号
     */
    private String outTradeNo;

    /**
     * PAYJS 订单号
     */
    private String payjsOrderId;

    /**
     * 微信显示订单号
     */
    private String transactionId;

    /**
     * 	0：未支付，1：支付成功
     */
    private Integer status;

    /**
     * 用户 OPENID
     */
    private String openid;

    /**
     * 订单支付时间
     */
    private String paidTime;

    /**
     * 用户自定义数据
     */
    private String attach;

    /**
     * 数据签名
     */
    private String sign;

}

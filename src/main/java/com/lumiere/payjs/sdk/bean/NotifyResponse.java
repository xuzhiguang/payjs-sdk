package com.lumiere.payjs.sdk.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author xuzhiguang
 * @date 2018/12/20
 */
@Data
@ToString(callSuper = true)
public class NotifyResponse extends PayJsResponse {

    /**
     * 金额。单位：分
     */
    private Integer totalFee;

    /**
     * 用户侧订单号
     */
    private String outTradeNo;

    /**
     * payjs端订单号
     */
    private String payjsOrderId;

    /**
     * 微信侧支付订单号
     */
    private String transactionId;

    /**
     * 订单结束时间
     */
    private String timeEnd;

    /**
     * 用户openid
     */
    private String openid;

    /**
     * 用户自定义数据
     */
    private String attach;

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 签名
     */
    private String sign;

}

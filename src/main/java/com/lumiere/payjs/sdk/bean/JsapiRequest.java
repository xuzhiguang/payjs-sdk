package com.lumiere.payjs.sdk.bean;

import lombok.Data;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
@Data
public class JsapiRequest {

    /**
     * 商户号，自动填充，可不填
     */
    private String mchid;

    /**
     * 支付金额，单位分（必填）
     */
    private Integer totalFee;

    /**
     * 用户端自主生成的订单号，在用户端要保证唯一性（必填）
     */
    private String outTradeNo;

    /**
     * 订单标题
     */
    private String body;

    /**
     * 用户自定义数据
     */
    private String attach;

    /**
     * 接收微信支付异步通知的回调地址。必须为可直接访问的URL，不能带参数、session验证、csrf验证。留空则不通知
     */
    private String notifyUrl;

    /**
     * openid（必填）
     */
    private String openid;

    /**
     * 数据签名
     */
    private String sign;
}

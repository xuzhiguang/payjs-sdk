package com.xuzhiguang.payjs.sdk.bean;

import lombok.Data;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
@Data
public class CashierRequest {

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
     * 用户支付成功后，前端跳转地址。留空则支付后关闭webview
     */
    private String callbackUrl;

    /**
     * 	auto=1：无需点击支付按钮，自动发起支付。默认手动点击发起支付
     */
    private Integer auto;

    /**
     * hide=1：隐藏收银台背景界面。默认显示背景界面（这里hide为1时，自动忽略auto参数）
     */
    private Integer hide;

    /**
     * 数据签名
     */
    private String sign;

}

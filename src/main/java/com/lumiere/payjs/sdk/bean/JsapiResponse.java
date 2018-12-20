package com.lumiere.payjs.sdk.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
@Data
@ToString(callSuper = true)
public class JsapiResponse extends PayJsResponse {

    /**
     * 返回信息
     */
    private String returnMsg;

    /**
     * payjs端订单号
     */
    private String payjsOrderId;

    /**
     * 	用于发起支付的支付参数
     */
    private String jsapi;

    /**
     * 数据签名
     */
    private String sign;

}

package com.lumiere.payjs.sdk.bean;

import lombok.Data;

/**
 * @author xuzhiguang
 * @date 2018/12/20
 */
@Data
public class RefundRequest {

    /**
     * payjs平台订单号
     */
    private String payjsOrderId;

    /**
     * 签名
     */
    private String sign;

}

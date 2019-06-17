package com.xuzhiguang.payjs.sdk.bean;

import lombok.Data;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
@Data
public class CheckRequest {

    /**
     * payjs平台订单号
     */
    private String payjsOrderId;

    /**
     * 签名
     */
    private String sign;

}

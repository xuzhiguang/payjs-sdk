package com.lumiere.payjs.sdk.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
@Data
@ToString(callSuper = true)
public class CloseResponse extends PayJsResponse {

    /**
     * 返回消息
     */
    private String returnMsg;

    /**
     * PAYJS 平台订单号
     */
    private String payjsOrderId;

    /**
     * 数据签名
     */
    private String sign;
}

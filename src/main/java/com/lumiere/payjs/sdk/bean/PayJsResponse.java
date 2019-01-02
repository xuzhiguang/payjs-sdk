package com.lumiere.payjs.sdk.bean;

import com.lumiere.payjs.sdk.constant.PayJsConstant;
import lombok.Data;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
@Data
public class PayJsResponse {

    /**
     * 返回标识
     */
    private Integer returnCode;

    /**
     * 判断是否成功
     * @return
     */
    public boolean isSuccess() {
        return this.returnCode.equals(PayJsConstant.ReturnCode.SUCCESS);
    }
}

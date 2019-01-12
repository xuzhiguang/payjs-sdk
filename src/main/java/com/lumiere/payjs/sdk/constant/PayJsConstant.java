package com.lumiere.payjs.sdk.constant;

/**
 * @author xuzhiguang
 * @date 2019/1/2
 */
public class PayJsConstant {

    /**
     * 返回状态码
     */
    public static class ReturnCode {

        /**
         * 成功
         */
        public static final int SUCCESS = 1;

        /**
         * 失败
         */
        public static final int FAIL = 0;

    }

    public static class OrderStatus {

        /**
         * 已支付
         */
        public static final int PAIED = 1;

        /**
         * 未支付
         */
        public static final int NOT_PAY = 0;
    }

}

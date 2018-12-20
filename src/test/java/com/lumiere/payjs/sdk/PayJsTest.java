package com.lumiere.payjs.sdk;

import com.lumiere.payjs.sdk.bean.CashierRequest;
import org.junit.Test;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
public class PayJsTest {

    PayJs payJs = new PayJs("111", "111");

    @Test
    public void cashier() {
        CashierRequest cashierRequest = new CashierRequest();
        cashierRequest.setOutTradeNo(System.currentTimeMillis() + "");
        cashierRequest.setTotalFee(1);
        cashierRequest.setAuto(1);
        cashierRequest.setHide(1);
        String url = payJs.cashier(cashierRequest);
        System.out.println(url);
    }

    @Test
    public void close() {
    }

    @Test
    public void check() {


    }
}
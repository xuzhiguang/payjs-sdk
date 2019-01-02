package com.lumiere.payjs.sdk;

import com.lumiere.payjs.sdk.bean.*;
import com.lumiere.payjs.sdk.exception.PayJsException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author xuzhiguang
 * @date 2019/1/2
 */
@Slf4j
public class PayJsTest {

    String mchid = "xxxx";

    String key = "xxxx";

    PayJs payJs = new PayJs(mchid, key);

    /**
     * 退款
     * @throws PayJsException
     */
    @Test
    public void refund() throws PayJsException {
        RefundRequest request = new RefundRequest();
        // payjs端订单号
        final String payJsOrderId = "xxxxx";

        request.setPayjsOrderId(payJsOrderId);
        RefundResponse response;
        try {
            response = payJs.refund(request);
        } catch (PayJsException e) {
            log.error("退款失败，异常信息：{}", e);
            throw new PayJsException("退款失败");
        }
        if (response.isSuccess()) {
            log.info("成功");
        } else {
            log.info("失败");
        }
    }

    /**
     * jsapi参数获取
     * @throws PayJsException
     */
    @Test
    public void jsapi() throws PayJsException {

        JsapiRequest request = new JsapiRequest();
        request.setAttach("用户自定义数据");
        request.setBody("订单标题");
        // 回调地址
        request.setNotifyUrl("http://xxx.com/notify");
        // 用户openid 获取openid请参考 https://help.payjs.cn/api-lie-biao/huo-qu-openid.html
        request.setOpenid("openid");
        // 订单号
        request.setOutTradeNo("xxxxx");
        // 订单金额，单位（分）
        request.setTotalFee(1);

        PayJsResponse response;
        try {
            response = payJs.jsapi(request);
        } catch (PayJsException e) {
            log.error("获取jsapi参数失败，异常信息：{}", e);
            throw new PayJsException("获取jsapi参数失败");
        }

        if (response.isSuccess()) {
            log.info("成功");
        } else {
            log.info("失败");
        }
    }

    /**
     * 收银台支付
     */
    @Test
    public void cashier() {

        CashierRequest request = new CashierRequest();
        request.setAttach("用户自定义数据");
        request.setBody("订单标题");
        // 回调地址
        request.setNotifyUrl("http://xxx.com/notify");
        // 页面跳转地址，支付成功后会跳转到该地址
        request.setCallbackUrl("http://xxx.com/");
        // auto=1：无需点击支付按钮，自动发起支付。默认手动点击发起支付
        request.setAuto(1);
        // hide=1：隐藏收银台背景界面。默认显示背景界面（这里hide为1时，自动忽略auto参数）
        request.setHide(1);
        // 订单号
        request.setOutTradeNo("xxxxx");
        // 订单金额，单位（分）
        request.setTotalFee(1);

        // 直接返回收银台支付url，请前台直接跳转
        String payUrl = payJs.cashier(request);

        log.info("支付url为:{}", payUrl);

    }

    /**
     * 关闭订单
     * @throws PayJsException
     */
    @Test
    public void close() throws PayJsException {

        CloseRequest request = new CloseRequest();
        // payjs端订单号
        final String payJsOrderId = "xxxxx";

        request.setPayjsOrderId(payJsOrderId);

        CloseResponse response;
        try {
            response = payJs.close(request);
        } catch (PayJsException e) {
            log.error("关闭订单失败，异常信息：{}", e);
            throw new PayJsException("关闭订单失败");
        }

        if (response.isSuccess()) {
            log.info("成功");
        } else {
            log.info("失败");
        }
    }

    /**
     * 查询订单
     * @throws PayJsException
     */
    @Test
    public void check() throws PayJsException {

        CheckRequest request = new CheckRequest();
        // payjs端订单号
        final String payJsOrderId = "xxxxx";

        request.setPayjsOrderId(payJsOrderId);

        CheckResponse response;
        try {
            response = payJs.check(request);
        } catch (PayJsException e) {
            log.error("查询订单失败，异常信息：{}", e);
            throw new PayJsException("查询订单失败");
        }

        if (response.isSuccess()) {
            log.info("成功");
        } else {
            log.info("失败");
        }
    }
}
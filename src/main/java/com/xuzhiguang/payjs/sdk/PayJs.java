package com.xuzhiguang.payjs.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.lumiere.payjs.sdk.bean.*;
import com.xuzhiguang.payjs.sdk.exception.PayJsException;
import com.xuzhiguang.payjs.sdk.util.SignUtil;
import com.xuzhiguang.payjs.sdk.bean.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiguang
 * @date 2018/12/19
 */
@Slf4j
public class PayJs {

    private final MediaType MediaTypeJson = MediaType.parse("application/json; charset=utf-8");

    /**
     * 域名
     */
    private final String host = "https://payjs.cn";

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 秘钥
     */
    private String key;

    /**
     * 连接超时时间
     */
    private long connectTimeout = 3000L;

    /**
     * 读取超时时间
     */
    private long readTimeout = 5000L;

    public PayJs(String mchid, String key) {
        this.mchid = mchid;
        this.key = key;
    }

    public PayJs(String mchid, String key, long connectTimeout, long readTimeout) {
        this.mchid = mchid;
        this.key = key;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    /**
     * 获取回调数据
     * 官方文档<a>https://help.payjs.cn/api-lie-biao/jiao-yi-xin-xi-tui-song.html</a>
     * @param response 回调数据
     * @return 回调数据
     * @throws PayJsException 异常
     */
    public NotifyResponse notify(NotifyResponse response) throws PayJsException {
        String sign = response.getSign();
        response.setSign(null);
        String sign1 = SignUtil.getSign(response, this.key);
        if (!sign.equals(sign1)) {
            throw new PayJsException("签名不匹配");
        }
        return response;
    }

    /**
     * 退款
     * 官方文档<a>https://help.payjs.cn/api-lie-biao/tui-kuan.html</a>
     * @param request 请求数据
     * @return 返回数据
     * @throws PayJsException 异常
     */
    public RefundResponse refund(RefundRequest request) throws PayJsException {
        final String url = this.host + "/api/refund";
        request.setSign(SignUtil.getSign(request, this.key));

        return this.doRequest(url, request, RefundResponse.class);
    }

    /**
     * 获取jsapi所需参数
     * 官方文档<a>https://help.payjs.cn/api-lie-biao/jsapiyuan-sheng-zhi-fu.html</a>
     * @param request 请求数据
     * @return 返回数据
     * @throws PayJsException 异常
     */
    public JsapiResponse jsapi(JsapiRequest request) throws PayJsException {
        final String url = this.host + "/api/jsapi";
        request.setSign(SignUtil.getSign(request, this.key));

        return this.doRequest(url, request, JsapiResponse.class);
    }

    /**
     * 收银台支付
     * 官方文档<a>https://help.payjs.cn/api-lie-biao/shou-yin-tai-zhi-fu.html</a>
     * @param request 请求数据
     * @return 返回URL地址，需要前端跳转
     */
    public String cashier(CashierRequest request) {

        final String url = this.host + "/api/cashier";

        request.setMchid(this.mchid);
        request.setSign(SignUtil.getSign(request, this.key));

        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String jsonString = JSONObject.toJSONString(request, config);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        // 拼接url
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        stringBuilder.append("?");
        jsonObject.entrySet().forEach(entry -> stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "&"));

        return stringBuilder.toString();
    }

    /**
     * 关闭订单
     * 官方文档<a>https://help.payjs.cn/api-lie-biao/guan-bi-ding-dan.html</a>
     * @param request 请求数据
     * @return 返回数据
     * @throws PayJsException 异常
     */
    public CloseResponse close(CloseRequest request) throws PayJsException {
        final String url = this.host + "/api/close";
        request.setSign(SignUtil.getSign(request, this.key));

        return this.doRequest(url, request, CloseResponse.class);
    }

    /**
     * 订单查询
     * 官方文档<a>https://help.payjs.cn/api-lie-biao/ding-dan-cha-xun.html</a>
     * @param request 请求数据
     * @return 返回数据
     * @throws PayJsException 异常
     */
    public CheckResponse check(CheckRequest request) throws PayJsException {
        final String url = this.host + "/api/check";
        request.setSign(SignUtil.getSign(request, this.key));

        return this.doRequest(url, request, CheckResponse.class);
    }

    /**
     * 发送请求
     * @param url 请求地址
     * @param requestObj 请求数据对象
     * @param tClass 返回对象类
     * @param <T> payjs返回数据
     * @return payjs返回数据
     * @throws PayJsException 异常
     */
    private <T extends PayJsResponse> T doRequest(String url, Object requestObj, Class<T> tClass) throws PayJsException {

        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String jsonString = JSONObject.toJSONString(requestObj, config);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(this.connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(this.readTimeout, TimeUnit.MILLISECONDS)
                .build();

        RequestBody body = RequestBody.create(MediaTypeJson, jsonString);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("发送请求出错：{}", e);
            throw new PayJsException("发送请求错误:" + e.getMessage());
        }

        String responseBody;
        try {
            responseBody = response.body().string();
        } catch (IOException e) {
            log.error("解析返回数据出错：{}", e);
            throw new PayJsException("解析返回数据出错:" + e.getMessage());
        }

        return JSON.parseObject(responseBody, tClass);
    }
}

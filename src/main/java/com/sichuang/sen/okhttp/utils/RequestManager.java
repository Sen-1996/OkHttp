package com.sichuang.sen.okhttp.utils;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Sen
 * @desc 请求参数配置
 * @date 2017/11/1.
 */

public class RequestManager {

    //设置请求的超时时间(单位为秒)
    public static final int TIMEOUT = 25;

    //创建OkHttpClient
    protected final OkHttpClient.Builder builder = new OkHttpClient.Builder();

    /**
     * 发起同步请求
     *
     * @param request
     * @throws IOException
     */
    protected Response execute(Request request) throws IOException {
        builder.connectTimeout(25000, TimeUnit.MILLISECONDS);
        return builder.build().newCall(request).execute();
    }

    /**
     * 发起异步请求
     *
     * @param request
     * @return
     * @throws IOException
     */
    protected void enqueue(Request request, OkHttpCallback callback) throws IOException {
        builder.connectTimeout(25000, TimeUnit.MILLISECONDS);
        builder.retryOnConnectionFailure(true);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.build().newCall(request).enqueue(callback);
    }


}

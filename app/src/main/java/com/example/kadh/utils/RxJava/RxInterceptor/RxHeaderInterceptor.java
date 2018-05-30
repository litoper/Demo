package com.example.kadh.utils.RxJava.RxInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :请求添加
 */
public class RxHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Accept-Charset", "utf-8")
                .addHeader("Accept-Language", "zh-CN;q=0.5")
//                                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();
        return chain.proceed(request);
    }
}

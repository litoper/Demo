package com.example.kadh.utils.RxJava.RxInterceptor;

import android.util.Log;

import com.example.kadh.utils.RxJava.RxApi.RxApiUrl;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by metaire on 2017/4/20.
 * 公共参数统一处理
 */

public class ParamsInterceptor implements Interceptor {
    private static final String TAG = "request params";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request orgRequest = chain.request();


        RequestBody body = orgRequest.body();
        //收集请求参数，方便调试
        StringBuilder paramsBuilder = new StringBuilder();

        if (body != null) {

            RequestBody newBody = null;

            if (body instanceof FormBody) {
//                newBody = addParamsToFormBody((FormBody) body, paramsBuilder);
            } else if (body instanceof MultipartBody) {
//                newBody = addParamsToMultipartBody((MultipartBody) body, paramsBuilder);
            } else {
                HttpUrl.Builder authorizedUrlBuilder = orgRequest.url()
                        .newBuilder()
                        .scheme(orgRequest.url().scheme())
                        .host(orgRequest.url().host())
//                        .addQueryParameter("version", Constant.Url.Ver)
                        ;
                Request newRequest = orgRequest.newBuilder()
                        .method(orgRequest.method(), orgRequest.body())
                        .url(authorizedUrlBuilder.build())
                        .build();

                return chain.proceed(newRequest);
            }


            if (null != newBody) {
                //打印参数
                Log.i(TAG, paramsBuilder.toString());

                Request newRequest = orgRequest.newBuilder()
                        .url(orgRequest.url())
                        .method(orgRequest.method(), newBody)
                        .build();

                return chain.proceed(newRequest);
            }


        }

        return chain.proceed(orgRequest);

    }

    /**
     * 为MultipartBody类型请求体添加参数
     *
     * @param body
     * @param paramsBuilder
     * @return
     */
    private MultipartBody addParamsToMultipartBody(MultipartBody body, StringBuilder paramsBuilder) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("version", RxApiUrl.Url.Ver);


        paramsBuilder.append("version=" + RxApiUrl.Url.Ver);


        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addPart(body.part(i));
        }
        return builder.build();
    }

    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @param paramsBuilder
     * @return
     */
    private FormBody addParamsToFormBody(FormBody body, StringBuilder paramsBuilder) {
        FormBody.Builder builder = new FormBody.Builder();

        builder.add("version", RxApiUrl.Url.Ver);

        paramsBuilder.append("version=" + RxApiUrl.Url.Ver);


        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
            paramsBuilder.append("&");
            paramsBuilder.append(body.name(i));
            paramsBuilder.append("=");
            paramsBuilder.append(body.value(i));
        }
        return builder.build();
    }
}


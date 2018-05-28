package com.example.kadh.utils.RxJava.RxApi;


import android.content.pm.PackageManager;

import com.example.kadh.app.App;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxInterceptor.HttpLoggingInterceptor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by metaire on 2017/3/27.
 */

public class RxApiManager {
    private RxApiService mRxApiService;
    private String version;
    private static RxApiManager mInstance;
    private static final int CONN_TIMEOUT = 60;//连接超时时间,单位  秒
    private static final int READ_TIMEOUT = 60;//连接超时时间,单位  秒
    private static PersistentCookieJar sCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getApp()));
    //    private static CookiesManager mCookiesManager = new CookiesManager();

    private RxApiManager() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RxConstant.Url.BASE)
                .client(initOkHttp())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mRxApiService = retrofit.create(RxApiService.class);
        version = "android_" + getVerName();
    }

    public static String getVerName() {
        String verName = "unknown";
        try {
            verName = App.getApp().getPackageManager().getPackageInfo(App.getApp().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    public static RxApiManager getRxApi() {
        if (mInstance == null) {
            synchronized (RxApiManager.class) {
                mInstance = new RxApiManager();
            }
        }
        return mInstance;
    }

    private static OkHttpClient initOkHttp() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
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
                })
                .addNetworkInterceptor(new HttpLoggingInterceptor())//添加网络拦截器 打印日志
//                .addInterceptor(new ParamsInterceptor())//添加应用拦截器
//                .addInterceptor(new ProgressInterceptor())//添加下载监听拦截器
//                .addInterceptor(new ServerErrorInterceptor())
                .retryOnConnectionFailure(true)//错误重连
                .cookieJar(sCookieJar)
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public void getCode(Subscriber<BaseResponse<String>> subscriber) {
        Flowable flowable = mRxApiService.CheckVersion(version);
        toSubscribe(flowable, subscriber);
    }


    // * subscribeOn(): 指定 subscribe() 发生在 IO 线程
    // * observeOn(): 指定 Subscriber 的回调发生在主线程
    //添加线程管理并订阅
    private void toSubscribe(Flowable flowable, Subscriber s) {
        flowable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}

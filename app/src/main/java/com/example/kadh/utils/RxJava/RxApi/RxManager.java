package com.example.kadh.utils.RxJava.RxApi;

import com.example.kadh.BuildConfig;
import com.example.kadh.app.App;
import com.example.kadh.utils.RxJava.RxInterceptor.HttpLoggingInterceptor;
import com.example.kadh.utils.RxJava.RxInterceptor.RxHeaderInterceptor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/31
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class RxManager {

    private static final int CONN_TIMEOUT = 60;//连接超时时间,单位  秒
    private static final int READ_TIMEOUT = 60;//连接超时时间,单位  秒

    private static SharedPrefsCookiePersistor sCookiePersistor;
    private static PersistentCookieJar sCookieJar;
    private static RxApi sRxApi;
    private static RxManager sRxManager;
    private Cache mHttpCache;

    private RxManager() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RxApiUrl.Url.BASE)
                .client(initOkHttp())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        sRxApi = new RxApi(retrofit.create(RxApiService.class), "android_" + BuildConfig.VERSION_NAME);
    }

    public static RxManager getInstant() {
        if (sRxManager == null) {
            synchronized (RxManager.class) {
                sRxManager = new RxManager();
            }
        }
        return sRxManager;
    }

    public SharedPrefsCookiePersistor getCookie() {
        return sCookiePersistor;
    }

    public RxApi getRxApi() {
        return sRxApi;
    }

    public Cache getHttpCache() {
        return mHttpCache;
    }

    private OkHttpClient initOkHttp() {
        // Cookie 持久化
        sCookiePersistor = new SharedPrefsCookiePersistor(App.getApp());
        sCookieJar = new PersistentCookieJar(new SetCookieCache(), sCookiePersistor);
        // 指定缓存路径,缓存大小 50Mb
        mHttpCache = new Cache(new File(App.getApp().getCacheDir(), "HttpCache"), 1024 * 1024 * 50);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new RxHeaderInterceptor())
                .addNetworkInterceptor(new HttpLoggingInterceptor())//添加网络拦截器 打印日志
                //                .addInterceptor(new ParamsInterceptor())//添加应用拦截器
                //                .addInterceptor(new ProgressInterceptor())//添加下载监听拦截器
                //                .addInterceptor(new ServerErrorInterceptor())
                .retryOnConnectionFailure(true)//错误重连
                .cookieJar(sCookieJar)
                .cache(mHttpCache)
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }
}

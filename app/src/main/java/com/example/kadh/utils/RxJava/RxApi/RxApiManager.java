package com.example.kadh.utils.RxJava.RxApi;


import android.content.pm.PackageManager;

import com.example.kadh.app.App;
import com.example.kadh.ui.login.LoginModel;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxInterceptor.HttpLoggingInterceptor;
import com.example.kadh.utils.RxJava.RxInterceptor.RxHeaderInterceptor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */


public class RxApiManager {
    private static Cache mHttpCache;
    private RxApiService mRxApiService;
    private String version;
    private static RxApiManager mInstance;
    private static final int CONN_TIMEOUT = 60;//连接超时时间,单位  秒
    private static final int READ_TIMEOUT = 60;//连接超时时间,单位  秒

    private static SharedPrefsCookiePersistor sCookiePersistor;
    private static PersistentCookieJar sCookieJar;
    //    private static CookiesManager mCookiesManager = new CookiesManager();

    private RxApiManager() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RxConstant.Url.BASE)
                .client(initOkHttp())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    public PersistentCookieJar getCookieJar() {
        return sCookieJar;
    }

    public SharedPrefsCookiePersistor getCookiePersistor() {
        return sCookiePersistor;
    }

    private static OkHttpClient initOkHttp() {
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

    public void getCode(Observer<BaseResponse<String>> observer) {
        Observable observable = mRxApiService.checkVersion(version);
        toSubscribe(observable, observer);
    }


    public void login(Observer<BaseResponse<List<LoginModel>>> observer, String username, String password) {
        Observable observable = mRxApiService.login(username, password, version);
        toSubscribe(observable, observer);
    }


    // subscribeOn(): 指定 subscribe() 发生在 IO 线程
    // observeOn(): 指定 Subscriber 的回调发生在主线程
    //添加线程管理并订阅
    private void toSubscribe(Observable observable, Observer observer) {
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

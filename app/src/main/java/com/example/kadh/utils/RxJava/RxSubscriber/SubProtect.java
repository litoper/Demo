package com.example.kadh.utils.RxJava.RxSubscriber;


import android.util.Log;
import android.widget.Toast;

import com.example.kadh.BuildConfig;
import com.example.kadh.app.App;
import com.example.kadh.utils.NetworkUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.google.gson.JsonParseException;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.annotations.NonNull;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class SubProtect<T> extends DisposableSubscriber<T> {
    private SubNextImpl<T> mSubListener;

    public SubProtect(@NonNull SubNextImpl<T> subListener) {
        this.mSubListener = subListener;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSubListener.onSubStart();
        //检查是否有网络连接
        if (!NetworkUtils.isNetworkAvailable(App.getApp())) {
            mSubListener.onSubError(new ConnectException());
            Toast.makeText(App.getApp(), "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
            // 一定好主动调用下面这一句,取消本次Subscriber订阅
            dispose("无网络");
        }
    }

    @Override
    public void onNext(T t) {
        BaseResponse baseResponse = (BaseResponse) t;
        mSubListener.onSubNext(t);
        if (baseResponse.success) {
            mSubListener.onSubSuccess(t);
        } else {
            //110为未登录状态
            if ("110".equals(baseResponse.msg)) {
                Toast.makeText(App.getApp(), "110", Toast.LENGTH_SHORT).show();
                //                Intent intent = new Intent(mContext, LoginActivity.class);
                //                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //                mContext.startActivity(intent);
            } else {
                mSubListener.onSubFalse(t);
                //默认false给予吐司提示,上方case里内容的接口已做处理则不提示
                switch (baseResponse.msg) {
                    case "请升级到新版本，以支持流程新模式审批！":
                        break;
                    default:
                        Toast.makeText(App.getApp(), baseResponse.msg, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        //网络访问失败借口回调
        mSubListener.onSubError(e);
        paserExcetpion(e);
        dispose("网络错误");
    }

    private void paserExcetpion(Throwable e) {
        if (BuildConfig.DEBUG) {
            if (e instanceof SocketTimeoutException) {
                Toast.makeText(App.getApp(), "连接超时！", Toast.LENGTH_SHORT).show();
            } else if (e instanceof ConnectException) {
                Toast.makeText(App.getApp(), "无法连接到服务器！", Toast.LENGTH_SHORT).show();
            } else if (e instanceof FileNotFoundException) {
                Toast.makeText(App.getApp(), "服务器搬家了!", Toast.LENGTH_SHORT).show();
            } else if (e instanceof JsonParseException) {
                Toast.makeText(App.getApp(), "数据解析存在问题!", Toast.LENGTH_SHORT).show();
            } else if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                Toast.makeText(App.getApp(), "错误码:" + httpException.code() + "\r\n错误消息:" + httpException.message(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(App.getApp(), e.toString(), Toast.LENGTH_SHORT).show();
            }
            e.printStackTrace();
        } else {
            Toast.makeText(App.getApp(), "系统错误, 请联系研发中心处理!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete() {
        mSubListener.onSubCompleted();
        dispose("完成");
    }

    public void dispose(String origin) {
        Log.e("SubProtect", "Subscriber回收:" + origin + " isDisposed : " + this.isDisposed());
        if (!this.isDisposed()) {
            this.dispose();
        }
    }
}

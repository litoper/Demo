package com.example.kadh.service.pushreceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;


/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/7
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class UmengPushReceiver extends UmengNotificationClickHandler implements IUmengRegisterCallback {

    private static final String TAG = "UmengPushReceiver";
    private Context mContext;
    private Intent intent;
    private String type;
    private String allowSeeId;
    private String publishId;

    public UmengPushReceiver(Context context) {
        mContext = context;
    }

    //注册推送服务，每次调用register方法都会回调该接口
    @Override
    public void onSuccess(String deviceToken) {
        //注册成功会返回device token
        Log.d(TAG, "deviceToken:" + deviceToken);
//        SpManager.getInstance().putData(mContext, "MANUFACTURER", "brand", "ANDROID");
    }

    @Override
    public void onFailure(String s, String s1) {
        Log.d(TAG, "onFailure  s:" + s);
        Log.d(TAG, "onFailure  s1:" + s1);
    }

    //友盟推送点击回调
    @Override
    public void dealWithCustomAction(Context context, UMessage msg) {
        Log.d(TAG, "dealWithCustomAction() called with: msg.extra.toString() = [" + msg.extra.toString() + "]");
        if (msg.extra != null) {
            type = msg.extra.get("ptype");
            allowSeeId = msg.extra.get("allowSeeId");
            publishId = msg.extra.get("publishId");
            CommonDealPush dealPush = new CommonDealPush(context, type, allowSeeId, publishId);
            dealPush.doBusiness();
        }
    }
}

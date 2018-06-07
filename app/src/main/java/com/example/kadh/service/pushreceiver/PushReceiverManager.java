package com.example.kadh.service.pushreceiver;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.example.kadh.BuildConfig;
import com.example.kadh.app.App;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.utils.SpUtil;
import com.huawei.android.pushagent.PushException;
import com.huawei.android.pushagent.PushManager;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.os.Build.MANUFACTURER;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/7
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class PushReceiverManager {

    private Context mContext;
    private static final String TAG = "PushReceiverManager";
    private static PushReceiverManager mManager = null;
    private static final String DJOA = "DJOA";
    private static final String BRAND_XIAO_MI = "XIAOMI";
    private static final String BRAND_HUA_WEI = "HUAWEI";
    //小米正式key
//    private static final String APP_ID_XIAO_MI = "2882303761517603482";
//    private static final String APP_KEY_XIAO_MI = "5431760360482";

    //小米测试key
//    private static final String APP_ID_XIAO_MI = "2882303761517632811";
//    private static final String APP_KEY_XIAO_MI = "5281763252811";

    private static String mPushId;
    private static String mBrand;

    private PushReceiverManager(Context context) {
        mContext = context;
        mBrand = getDeviceModel();
        SpUtil.getInstance().put(SpUtil.INFO_PHONE_BRAND, mBrand);
    }

    public static synchronized PushReceiverManager getInstance() {
        if (mManager == null) {
            mManager = new PushReceiverManager(App.getApp());
        }
        return mManager;
    }

    /**
     * 判断手机厂商注册不同推送
     */
    public void initPushSdk() {
        switch (mBrand) {
            case BRAND_HUA_WEI:
                PushManager.requestToken(mContext);
                break;
            case BRAND_XIAO_MI:
                if (shouldInit()) {
                    MiPushClient.registerPush(mContext, BuildConfig.XIAO_MI_PUSH_ID, BuildConfig.XIAO_MI_PUSH_KEY);
                }
                break;
            default:
                PushAgent mPushAgent = PushAgent.getInstance(mContext);
                UmengPushReceiver pushReceiver = new UmengPushReceiver(mContext);
                mPushAgent.register(pushReceiver);
                mPushAgent.setNotificationClickHandler(pushReceiver);
                mPushAgent.setDebugMode(false);
                break;
        }
        Log.d(TAG, "initPushSdk(),PhoneBrand:" + mBrand);
    }

    /**
     * 设置Alias别名
     * 1.友盟和小米的Alias,OA这边是帐号登录后,服务器返回来的pushId
     * 2.华为pushId由HuaWeiPushReceiver中onToken()返回的
     * 3.设置别名后,会发送pushId跟服务器确认下
     */
    public void setAlias() {
        switch (mBrand) {
            case BRAND_HUA_WEI:
                mPushId = SpUtil.getInstance().getString(SpUtil.PUSH_INFO_HUA_WEI_TOKEN);
                setAliasForHuaWei();
                break;
            case BRAND_XIAO_MI:
                mPushId = SpUtil.getInstance().getString(SpUtil.LOGIN_INFO_PUSHID);
                setAliasForXiaoMi();
                break;
            default:
                mPushId = SpUtil.getInstance().getString(SpUtil.LOGIN_INFO_PUSHID);
                setAliasForUmeng();
                break;
        }
    }

    /**
     * 帐号退出时,清除对应的Alias别名
     */
    public void unsetAlias() {
        switch (mBrand) {
            case BRAND_HUA_WEI:
                unsetAliasForHuaWei();
                break;
            case BRAND_XIAO_MI:
                unsetAliasForXiaoMi();
                break;
            default:
                unsetAliasForUmeng();
                break;
        }
        Log.d(TAG, "unsetAlias() brand:" + mBrand + "  PushId:" + mPushId);
    }


    /**
     * 因为推送服务XMPushService在AndroidManifest.xml中设置为运行在另外一个进程
     * 这导致本Application会被实例化两次，所以我们需要让应用的主进程初始化。
     */
    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) App.getApp().getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = mContext.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }


    private void setAliasForHuaWei() {
        HashMap<String, String> map = new HashMap<>();
        map.put(DJOA, mPushId);
        try {
            PushManager.setTags(mContext, map);
        } catch (PushException e) {
            e.printStackTrace();

        }

        /*
        跟服务器请求确认,其他方法内一样
         */
        RxManager.getInstant().getRxApi().mqRegister(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
            @Override
            public void onSubSuccess(BaseResponse<String> response) {
                Log.d(TAG, "华为手机推送注册成功,PushId(token):" + mPushId);
            }
        }), mPushId, mBrand);
    }


    private void setAliasForXiaoMi() {
        RxManager.getInstant().getRxApi().mqRegister(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
            @Override
            public void onSubSuccess(BaseResponse<String> response) {
                Log.d(TAG, "小米手机推送注册成功,PushId:" + mPushId);
            }
        }), mPushId, mBrand);
    }

    private void setAliasForUmeng() {
        PushAgent mPushAgent = PushAgent.getInstance(mContext);
        mPushAgent.onAppStart();
        mPushAgent.setDebugMode(false);
        mPushAgent.addAlias(mPushId, DJOA, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, final String s) {
                if (b) {
                    RxManager.getInstant().getRxApi().mqRegister(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
                        @Override
                        public void onSubSuccess(BaseResponse<String> response) {
                            Log.d(TAG, "友盟推送注册成功,PushId:" + mPushId);
                        }
                    }), mPushId, mBrand);
                }
            }
        });
    }


    private void unsetAliasForUmeng() {
        PushAgent mPushAgent = PushAgent.getInstance(mContext);
        mPushAgent.removeAlias(mPushId, DJOA, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                if (b) {
                    RxManager.getInstant().getRxApi().mqLogOut(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
                        @Override
                        public void onSubSuccess(BaseResponse<String> response) {
                            clearUserInfo();
                        }
                    }), mPushId, mBrand);
                }
            }
        });
    }

    private void unsetAliasForXiaoMi() {
        RxManager.getInstant().getRxApi().mqLogOut(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
            @Override
            public void onSubSuccess(BaseResponse<String> response) {
                MiPushClient.unsetAlias(mContext, mPushId, null);
                clearUserInfo();
            }
        }), mPushId, mBrand);
    }

    private void unsetAliasForHuaWei() {
        RxManager.getInstant().getRxApi().mqLogOut(new SubProtect<BaseResponse<String>>(new SubNextImpl<BaseResponse<String>>() {
            @Override
            public void onSubSuccess(BaseResponse<String> response) {
                List<String> list = new ArrayList<String>();
                list.add(mPushId);
                try {
                    PushManager.deleteTags(mContext, list);
                } catch (PushException e) {
                    e.printStackTrace();
                } finally {
                    clearUserInfo();
                }
            }
        }), mPushId, mBrand);
    }

    /**
     * 获取手机厂商
     */
    private String getDeviceModel() {
        String carrier = MANUFACTURER;
        if (carrier != null) {
            Log.d(TAG, "getDeviceModel(): " + carrier);
            return carrier.toUpperCase();
        }
        Log.d(TAG, "getDeviceModel(): unknow");
        return "unknow";
    }

    /**
     * 清除cookie及登录信息
     */
    private void clearUserInfo() {
        RxManager.getInstant().getCookie().clear();
        SpUtil.getInstance().remove(SpUtil.LOGIN_INFO_PUSHID);
        SpUtil.getInstance().remove(SpUtil.LOGIN_INFO_USERID);
        SpUtil.getInstance().remove(SpUtil.LOGIN_INFO_USERICO);
        SpUtil.getInstance().remove(SpUtil.LOGIN_INFO_USERNAME);
    }
}

package com.example.kadh.service.pushreceiver;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.kadh.utils.SpUtil;
import com.huawei.android.pushagent.PushReceiver;
import com.huawei.android.pushagent.api.PushEventReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/7
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class HuaWeiPushReceiver extends PushEventReceiver {
    private Intent intent;
    private String type;
    private String allowSeeId;
    private String publishId;
    private JSONObject ob;
    private static final String TAG = "HuaWeiPushReceiver";

    @Override
    public void onToken(Context context, String token, Bundle extras) {
        String belongId = extras.getString("belongId");
        String content = "获取token和belongId成功，token = " + token + ",belongId = " + belongId;
        Log.e(TAG, content);
        SpUtil.getInstance().put(SpUtil.PUSH_INFO_HUA_WEI_TOKEN, token);
    }


    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        try {
            String content = "收到一条Push消息： " + new String(msg, "UTF-8");
            Log.e(TAG, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onEvent(final Context context, PushReceiver.Event event, Bundle extras) {
        Log.e(TAG, "onEvent() called with: event = [" + event + "], extras = " + "[" + extras + "]");
        if (PushReceiver.Event.NOTIFICATION_OPENED.equals(event) || PushReceiver.Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            int notifyId = extras.getInt(PushReceiver.BOUND_KEY.pushNotifyId, 0);
            if (0 != notifyId) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
            try {
                JSONArray ar = new JSONArray(extras.getString(BOUND_KEY.pushMsgKey));
                ob = ar.getJSONObject(0);
                type = ob.getString("ptype");
                allowSeeId = ob.getString("allowSeeId");
                publishId = ob.getString("publishId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (ob != null) {
                CommonDealPush dealPush = new CommonDealPush(context, type, allowSeeId, publishId);
                dealPush.doBusiness();
            }

            String content = "收到通知附加消息： " + extras.getString(PushReceiver.BOUND_KEY.pushMsgKey);
            Log.e(TAG, content);
        } else if (PushReceiver.Event.PLUGINRSP.equals(event)) {
            final int TYPE_LBS = 1;
            final int TYPE_TAG = 2;
            int reportType = extras.getInt(PushReceiver.BOUND_KEY.PLUGINREPORTTYPE, -1);
            boolean isSuccess = extras.getBoolean(PushReceiver.BOUND_KEY.PLUGINREPORTRESULT, false);
            String message = "";
            if (TYPE_LBS == reportType) {
                message = "LBS report result :";
            } else if (TYPE_TAG == reportType) {
                message = "TAG report result :";
            }
            Log.e(TAG, message + isSuccess);
        }
        super.onEvent(context, event, extras);
    }

    @Override
    public void onPushState(Context context, boolean b) {
        try {
            String content = "---------The current push status： " + (b ? "Connected" :
                    "Disconnected");
            Log.d(TAG, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

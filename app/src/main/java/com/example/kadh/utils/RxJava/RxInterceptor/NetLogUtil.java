package com.example.kadh.utils.RxJava.RxInterceptor;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import okhttp3.Response;

/**
 * @author: nicai
 * @email : nicaicai
 * @blog : nicaicaicai
 * @time : 2018/1/17
 * @desc :
 */

public class NetLogUtil {

    private static String TAG = "NetLog";
    private static String TOP_LEFT_CORNER = "┌";
    private static String BOTTOM_LEFT_CORNER = "└";
    private static String MIDDLE_CORNER = "├";
    private static String HORIZONTAL_LINE = "│";
    private static String DOUBLE_DIVIDER = "────────────────────────────────────────────────────────";
    private static String SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;


    public synchronized static void parse(String title, String url, String result, double time, Response response, Boolean shouldPrint) {
        if (shouldPrint) {
            Log.i(TAG, TOP_BORDER);

            Log.i(TAG, HORIZONTAL_LINE
                    + " " + title
                    + " " + url
                    + " " + String.format(Locale.getDefault(), "in %.1fms", time));

            Log.i(TAG, HORIZONTAL_LINE + " " + String.format(Locale.CHINA, "Received response is %s ,message[%s]," + "code[%d]",
                    response.isSuccessful() ? "success" : "fail", response.message(), response.code()));
            Log.i(TAG, "json:" + result);

            Log.i(TAG, MIDDLE_BORDER);

            Log.i(TAG, HORIZONTAL_LINE + " ");
            String message = fmtJson(result);

            Log.i(TAG, HORIZONTAL_LINE + ("Request".equals(title) ? " Params:" : " Result:"));

            for (String s : message.split("\n")) {
                Log.i(TAG, HORIZONTAL_LINE + " " + s);
            }

            Log.i(TAG, HORIZONTAL_LINE + " ");

            Log.i(TAG, BOTTOM_BORDER);
        }
    }

    private synchronized static String fmtJson(String result) {
        String message = "";
        try {
            String json = result.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(2);
                return message;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(2);
                return message;
            }
            message = json;
        } catch (JSONException e) {
            e.printStackTrace();
            message = result;
        }
        return message;
    }
}

package com.example.kadh.utils.RxJava.RxSubscriber;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class SubDialog {
    public static final int NODIALOG = 0;
    public static final int LOADING = 1;
    public static final int LOGINING = 2;
    public static final int UPLOADING = 3;
    public static final int LAUNCHING = 4;
    public static final int DOWNLOADING = 5;
    public static final int SYNCING = 6;

    private SweetAlertDialog mSweetAlertDialog;

    private Context context;
    private SubProtect mSubProtect;
    private boolean mCancelable;
    private int mTag;

    public SubDialog(Context context, SubProtect subProtect, boolean cancelable, int tag) {
        this.context = context;
        mSubProtect = subProtect;
        mCancelable = cancelable;
        mTag = tag;
    }


    @SuppressLint("NewApi")
    public void initSubDialog() {
        if (mSweetAlertDialog == null) {
            mSweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            switch (mTag) {
                case LOADING:
                    mSweetAlertDialog.setTitleText("加载中...");
                    break;
                case LOGINING:
                    mSweetAlertDialog.setTitleText("登录中...");
                    break;
                case UPLOADING:
                    mSweetAlertDialog.setTitleText("上传中...");
                    break;
                case LAUNCHING:
                    mSweetAlertDialog.setTitleText("发布中...");
                    break;
                case DOWNLOADING:
                    mSweetAlertDialog.setTitleText("下载中...");
                    break;
                case SYNCING:
                    mSweetAlertDialog.setTitleText("同步中...");
                    break;
                default:
                    break;
            }
            if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                mSweetAlertDialog.show();
            }
//            if (mCancelable) {
//                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        if (mSubProtect != null) {
//                            mSubProtect.dismissSubDialog("触摸");
//                        }
//                    }
//                });
//            } else {
//                mSweetAlertDialog.setCancelable(mCancelable);
//            }
//            if (!mSweetAlertDialog.isShowing() && !((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
//                mSweetAlertDialog.show();
//            }
        }
    }

    @SuppressLint("NewApi")
    public void dismissSubDialog() {
        if (mSweetAlertDialog != null) {
            if (mSweetAlertDialog.isShowing()) {
                //https://stackoverflow.com/questions/2745061/java-lang-illegalargumentexception-view-not-attached-to-window-manager
                Context context = ((ContextWrapper) mSweetAlertDialog.getContext()).getBaseContext();
                if (context instanceof Activity) {
                    if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                        mSweetAlertDialog.dismiss();
                    }
                } else {
                    mSweetAlertDialog.dismiss();
                }
            }
            mSweetAlertDialog = null;
        }
    }
}
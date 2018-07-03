package com.example.kadh.utils.RxJava.RxSubscriber;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import com.afollestad.materialdialogs.MaterialDialog;


/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class SubMdDialog {
    public static final int NODIALOG = 0;
    public static final int LOADING = 1;
    public static final int LOGINING = 2;
    public static final int UPLOADING = 3;
    public static final int LAUNCHING = 4;
    public static final int DOWNLOADING = 5;
    public static final int SYNCING = 6;


    private Context mContext;
    private SubProgress mSubProtect;
    private boolean mCancelable;
    private int mTag;
    private MaterialDialog mMaterialDialog;

    public SubMdDialog(Context context, SubProgress subProtect, boolean cancelable, int tag) {
        this.mContext = context;
        mSubProtect = subProtect;
        mCancelable = cancelable;
        mTag = tag;
    }


    @SuppressLint("NewApi")
    public void initSubDialog() {
        if (mMaterialDialog == null) {
            mMaterialDialog = new MaterialDialog.Builder(mContext).canceledOnTouchOutside(mCancelable).progress(true, 0).build();
            switch (mTag) {
                case LOADING:
                    mMaterialDialog.setContent("加载中...");
                    break;
                case LOGINING:
                    mMaterialDialog.setContent("登录中...");
                    break;
                case UPLOADING:
                    mMaterialDialog.setContent("上传中...");
                    break;
                case LAUNCHING:
                    mMaterialDialog.setContent("发布中...");
                    break;
                case DOWNLOADING:
                    mMaterialDialog.setContent("下载中...");
                    break;
                case SYNCING:
                    mMaterialDialog.setContent("同步中...");
                    break;
                default:
                    mMaterialDialog.setContent("加载中...");
                    break;
            }
            if (!((Activity) mContext).isFinishing() && !((Activity) mContext).isDestroyed()) {
                mMaterialDialog.show();
            }
//            if (mCancelable) {
//                mMaterialDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        if (mSubProtect != null) {
//                            mSubProtect.dismissSubDialog("触摸");
//                        }
//                    }
//                });
//            } else {
//                mMaterialDialog.setCancelable(mCancelable);
//            }
//            if (!mMaterialDialog.isShowing() && !((Activity) mContext).isFinishing() && !((Activity) mContext).isDestroyed()) {
//                mMaterialDialog.show();
//            }
        }
    }

    @SuppressLint("NewApi")
    public void dismissSubDialog() {
        if (mMaterialDialog != null) {
            if (mMaterialDialog.isShowing()) {
                //https://stackoverflow.com/questions/2745061/java-lang-illegalargumentexception-view-not-attached-to-window-manager
                Context context = ((ContextWrapper) mMaterialDialog.getContext()).getBaseContext();
                if (context instanceof Activity) {
                    if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                        mMaterialDialog.dismiss();
                    }
                } else {
                    mMaterialDialog.dismiss();
                }
            }
            mMaterialDialog = null;
        }
    }
}
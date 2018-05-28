package com.example.kadh.utils.RxJava;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private SweetAlertDialog pd;

    private Context context;
    private boolean cancelable;
    private int tag;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener, boolean cancelable, int tag) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
        this.tag = tag;
    }

    private void initProgressDialog() {
        if (pd == null) {
//            pd = new SweetAlertDialog(context);
            pd = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            switch (tag) {
                case 1:
                    pd.setTitleText("加载中");
                    break;
                case 2:
                    pd.setTitleText("登录中");
                    break;
                case 3:
                    pd.setTitleText("上传中");
                    break;
                case 4:
                    pd.setTitleText("发布中");
                    break;
                case 5:
                    pd.setTitleText("下载中");
                    break;
                case 6:
                    pd.setTitleText("同步中");
                    break;
                default:
                    break;
            }
            if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                pd.show();
            }
            if (cancelable) {
                pd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (mProgressCancelListener != null) {
                            mProgressCancelListener.onCancelProgress("dialog.onDismiss()");
                        }
                    }
                });
            } else {
                pd.setCancelable(cancelable);
            }
            if (!pd.isShowing() && !((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (pd != null) {
            if (pd.isShowing()) {
                //https://stackoverflow.com/questions/2745061/java-lang-illegalargumentexception-view-not-attached-to-window-manager
                Context context = ((ContextWrapper) pd.getContext()).getBaseContext();
                if (context instanceof Activity) {
                    if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                        pd.dismiss();
                    }
                } else {
                    pd.dismiss();
                }
            }
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
            default:
                break;
        }
    }
}
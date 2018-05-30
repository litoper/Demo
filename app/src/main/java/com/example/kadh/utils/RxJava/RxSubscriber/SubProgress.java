//package com.example.kadh.utils.RxJava.RxSubscriber;

/**
 * @author: nicai
 * @email : nicaicai
 * @blog : nicaicaicai
 * @time : 2017/12/5
 * @desc :
 */

//public class SubProgress<T> extends Subscriber<T> implements ProgressCancelListener {
//
////    public static final String PROFRESS_LOADING = "加载中...";
////    public static final String PROFRESS_LOGINING = "登录中...";
////    public static final String PROFRESS_UPLOADING = "上传中...";
////    public static final String PROFRESS_LAUNCHING = "发布中...";
////    public static final String PROFRESS_DOWNLOADING = "下载中...";
////    public static final String PROFRESS_UPDATING = "同步中...";
//
//    private SubOnNextImpl<T> mStateListener;
//    private Context mContext;
//    private ProgressDialogHandler mHandler;
//
//    public SubProgress(SubOnNextImpl<T> mStateListener, Context context, int tag) {
//        this.mStateListener = mStateListener;
//        this.mContext = context;
//        if (tag != 0) {
//            this.mHandler = new ProgressDialogHandler(context, this, true, tag);
//        }
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        showProgressDialog();
//        if (mStateListener != null) {
//            mStateListener.onSubStart();
//        }
//        //检查是否有网络连接
//        if (!NetworkUtils.isNetworkAvailable(mContext)) {
//            if (mStateListener != null) {
//                mStateListener.onSubError(new ConnectException());
//            }
//            Toast.makeText(mContext, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
//            // 一定好主动调用下面这一句,取消本次Subscriber订阅
//            onCancelProgress("onStart()");
//        }
//    }
//
//    @Override
//    public void onCompleted() {
//        if (mStateListener != null) {
//            mStateListener.onSubCompleted();
//        }
//        onCancelProgress("onCompleted()");
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        if (mStateListener != null) {
//            //网络访问失败借口回调
//            mStateListener.onSubError(e);
//        }
//        if (BuildConfig.DEBUG) {
//            if (e instanceof SocketTimeoutException) {
//                Toast.makeText(App.getApp(), "连接超时！", Toast.LENGTH_SHORT).show();
//            } else if (e instanceof ConnectException) {
//                Toast.makeText(App.getApp(), "无法连接到服务器！", Toast.LENGTH_SHORT).show();
//            } else if (e instanceof FileNotFoundException) {
//                Toast.makeText(App.getApp(), "服务器搬家了!", Toast.LENGTH_SHORT).show();
//            } else if (e instanceof JsonParseException) {
//                Toast.makeText(App.getApp(), "数据解析存在问题!", Toast.LENGTH_SHORT).show();
//            } else if (e instanceof HttpException) {
//                HttpException httpException = (HttpException) e;
//                Toast.makeText(App.getApp(), "错误码:" + httpException.code() + "\r\n错误消息:" + httpException.message(), Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(App.getApp(), e.toString(), Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//            }
//        } else {
//
//            Toast.makeText(App.getApp(), "系统错误, 请联系研发中心处理!", Toast.LENGTH_SHORT).show();
//        }
//        onCancelProgress("onError()");
//    }
//
//    @Override
//    public void onNext(T t) {
//        BaseResponse baseResponse = (BaseResponse) t;
//
//        if (mStateListener != null) {
//            mStateListener.onMsgNext(t);
//        }
//
//        if (baseResponse.success) {
//            if (mStateListener != null) {
//                mStateListener.onSubNext(t);
//            }
//        } else {
//            //未登录状态
//            if ("110".equals(baseResponse.msg)) {
//                Toast.makeText(mContext, "110", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(mContext, LoginActivity.class);
////                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                mContext.startActivity(intent);
//            } else {
//
//                if (mStateListener != null) {
//                    mStateListener.onSubFailed(t);
//                }
//
//                switch (baseResponse.msg) {
//                    case "请升级到新版本，以支持流程新模式审批！":
//                        break;
//                    /**
//                     * 默认false给予吐司提示,上方case里内容的接口已做处理则不提示
//                     */
//                    default:
//                        Toast.makeText(App.getApp(), baseResponse.msg, Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        }
//    }
//
//
//    @Override
//    public void onCancelProgress(String from) {
//        Log.e("onCancelProgress", "from:" + from + "    isUnsubscribed: " + this.isUnsubscribed());
//        if (!this.isUnsubscribed()) {
//            this.unsubscribe();
//        }
//        dismissProgressDialog();
//    }
//
//    private void showProgressDialog() {
//        if (mHandler != null) {
//            mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
//        }
//    }
//
//    private void dismissProgressDialog() {
//        if (mHandler != null) {
//            mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
//            mHandler = null;
//        }
//    }
//
//}

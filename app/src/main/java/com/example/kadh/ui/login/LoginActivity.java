package com.example.kadh.ui.login;

import android.util.Log;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApiManager;
import com.example.kadh.utils.RxJava.RxSubscriber.RxObsProgress;
import com.example.kadh.utils.RxJava.RxSubscriber.SubOnNextImpl;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "88888888";

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        SubOnNextImpl<BaseResponse<List<LoginModel>>> loginListener = new SubOnNextImpl<BaseResponse<List<LoginModel>>>() {
            @Override
            public void onSubNext(BaseResponse<List<LoginModel>> baseResponse) {
                Log.d(TAG, "baseResponse:" + baseResponse.data);
            }

            @Override
            public void onSubFailed(BaseResponse<List<LoginModel>> listBaseResponse) {

            }
        };
        RxApiManager.getRxApi().login(new RxObsProgress<BaseResponse<List<LoginModel>>>(loginListener, this, 1), "1000047", "123456");
//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions.requestEach(
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_CALENDAR,
//                Manifest.permission.READ_CALL_LOG,
//                Manifest.permission.READ_CONTACTS,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.READ_SMS,
//                Manifest.permission.RECORD_AUDIO,
//                Manifest.permission.CAMERA,
//                Manifest.permission.CALL_PHONE,
//                Manifest.permission.SEND_SMS)
//                .subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//                        if (permission.granted) {
//                            // 用户已经同意该权限
//                            Log.d(TAG, permission.name + " 同意.");
//                        } else if (permission.shouldShowRequestPermissionRationale) {
//                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//                            Log.d(TAG, permission.name + " 拒绝.");
//                        } else {
//                            // 用户拒绝了该权限，并且选中『不再询问』
//                            Log.d(TAG, permission.name + "不再询问.");
//                        }
//                    }
//                }).isDisposed();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
}

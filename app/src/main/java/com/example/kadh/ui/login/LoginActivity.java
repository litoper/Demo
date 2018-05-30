package com.example.kadh.ui.login;

import android.util.Log;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxSubscriber.SubDialog;
import com.example.kadh.utils.RxJava.RxApi.RxApiManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProgress;
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
            public void onSubSuccess(BaseResponse<List<LoginModel>> baseResponse) {
                Log.d(TAG, "baseResponse:" + baseResponse.data);
            }
        };
        RxApiManager.getRxApi().login(new SubProgress<BaseResponse<List<LoginModel>>>(loginListener, this, SubDialog.NODIALOG), "1000047", "123456");
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
}

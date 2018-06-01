package com.example.kadh.ui.login.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.kadh.base.BaseFragmentPresenter;
import com.example.kadh.bean.LoginBean;
import com.example.kadh.ui.login.contract.LoginFragContract;
import com.example.kadh.utils.MD5helper;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxApi;
import com.example.kadh.utils.RxJava.RxSubscriber.SubDialog;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProgress;
import com.example.kadh.utils.SpUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/1
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginNormalPresenter extends BaseFragmentPresenter<LoginFragContract.View> implements LoginFragContract.Presenter<LoginFragContract.View> {

    private RxApi mRxApi;
    private Context mContext;

    @Inject
    public LoginNormalPresenter(RxApi rxApi, Context context) {
        mRxApi = rxApi;
        mContext = context;
    }

    @Override
    public void getSpData() {
        String username = SpUtil.getInstance().getString(SpUtil.LOGIN_USERNAME);
        String password = SpUtil.getInstance().getString(SpUtil.LOGIN_PASSWORD);
        mView.setSpData(username, password);
        login(username, password);
    }

    @Override
    public void login(String username, String password) {
        if (NullUtils.isEmpty(username) || NullUtils.isEmpty(password)) {
            Toast.makeText(mContext, "密码或者账户不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Observer<BaseResponse<List<LoginBean>>> progress = new SubProgress<>(mContext, SubDialog.LOGINING, new SubNextImpl<BaseResponse<List<LoginBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<LoginBean>> response) {
                Log.d("LoginNormalPresenter", "response:" + response);
            }
        });
        mRxApi.login(progress, username, MD5helper.MD5(password));
    }
}

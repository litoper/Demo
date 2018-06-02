package com.example.kadh.ui.login.presenter;

import android.app.Activity;

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

    private RxApi    mRxApi;
    private Activity mActivity;

    @Inject
    public LoginNormalPresenter(RxApi rxApi, Activity activity) {
        mRxApi = rxApi;
        mActivity = activity;
    }

    @Override
    public void getFastData() {
        //初始化快速登陆列表数据
    }

    @Override
    public void getLoginData() {
        String username = SpUtil.getInstance().getString(SpUtil.LOGIN_USERNAME);
        String password = SpUtil.getInstance().getString(SpUtil.LOGIN_PASSWORD);
        mView.setLoginData(username, password);
    }

    @Override
    public void saveLoginData(String username, String passwrod, LoginBean bean) {
        //保存账户密码
        SpUtil.getInstance().putString(SpUtil.LOGIN_USERNAME, username);
        SpUtil.getInstance().putString(SpUtil.LOGIN_PASSWORD, passwrod);
        //保存登陆返回信息
        SpUtil.getInstance().put(SpUtil.INFO_PUSHID, bean.getPushID());
        SpUtil.getInstance().put(SpUtil.INFO_USERID, bean.getUserid());
        SpUtil.getInstance().put(SpUtil.INFO_USERNAME, bean.getUserName());
        SpUtil.getInstance().put(SpUtil.INFO_USERICO, bean.getUserIco());
    }

    @Override
    public void login(final String username, final String password) {
        Observer<BaseResponse<List<LoginBean>>> progress = new SubProgress<>(mActivity, SubDialog.LOGINING, new SubNextImpl<BaseResponse<List<LoginBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<LoginBean>> response) {
                if (!NullUtils.isNull(response.data)) {
                    LoginBean loginBean = response.data.get(0);
                    if (NullUtils.isEmpty(loginBean.getPushID()) || NullUtils.isEmpty(loginBean.getUserIco()) || NullUtils.isEmpty(loginBean.getUserid()) ||
                            NullUtils.isEmpty(loginBean.getUserName())) {
                        mView.loginFail();
                    } else {
                        mView.loginSuccess();
                        saveLoginData(username, password, loginBean);

                    }
                }
            }
        });
        mRxApi.login(progress, username, MD5helper.MD5(password));
    }
}

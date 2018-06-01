package com.example.kadh.ui.login.fragment;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.EditText;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragmentView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.login.contract.LoginFragContract;
import com.example.kadh.ui.login.presenter.LoginNormalPresenter;
import com.example.kadh.utils.NullUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginNormalFragment extends BaseFragmentView<LoginNormalPresenter> implements LoginFragContract.View {

    @BindView(R.id.login_normal_btn_clear)
    Button mBtnClear;
    @BindView(R.id.login_normal_et_username)
    EditText mEtUsername;
    @BindView(R.id.login_normal_btn_eye)
    Button mBtnEye;
    @BindView(R.id.login_normal_et_password)
    EditText mEtPassword;
    @BindView(R.id.login_normal_btn_login)
    Button mBtnLogin;
    @BindView(R.id.login_normal_btn_fastlogin)
    Button mBtnFastlogin;
    @BindView(R.id.login_normal_btn_forget)
    Button mBtnForget;
    private String mUsername;
    private String mPasswrod;

    public static LoginNormalFragment newInstance() {
        return new LoginNormalFragment();
    }

    public LoginNormalFragment() {
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login_normal;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void configViews() {
        RxView.clicks(mBtnLogin).throttleLast(1, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {

            @Override
            public void accept(Object o) throws Exception {

                mPresenter.login(mEtUsername.getText().toString().trim(), mEtPassword.getText().toString().trim());
            }
        });
    }

    @Override
    protected void initDatas() {
        mPresenter.getSpData();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void setSpData(String username, String passwrod) {
        mUsername = username;
        mPasswrod = passwrod;

        if (!NullUtils.isEmpty(username)) {
            mEtUsername.setText(username);
        }
        if (!NullUtils.isEmpty(passwrod)) {
            mEtPassword.setText(passwrod);
        }
    }
}

package com.example.kadh.ui.login.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragment;
import com.example.kadh.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginVerifyFragment extends BaseFragment {


    @BindView(R.id.login_vercode_et_phone)
    EditText mEtPhone;
    @BindView(R.id.login_vercode_et_code)
    EditText mEtCode;
    @BindView(R.id.login_vercode_btn_sendCode)
    Button mBtnSendCode;
    @BindView(R.id.login_vercode_btn_verlogin)
    Button mBtnVerlogin;
    @BindView(R.id.login_vercode_btn_forget)
    Button mBtnForget;
    Unbinder unbinder;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login_verify;
    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @OnClick({R.id.login_vercode_et_phone, R.id.login_vercode_et_code, R.id.login_vercode_btn_sendCode, R.id.login_vercode_btn_verlogin, R.id.login_vercode_btn_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_vercode_et_phone:
                break;
            case R.id.login_vercode_et_code:
                break;
            case R.id.login_vercode_btn_sendCode:
                break;
            case R.id.login_vercode_btn_verlogin:
                break;
            case R.id.login_vercode_btn_forget:
                break;
        }
    }
}

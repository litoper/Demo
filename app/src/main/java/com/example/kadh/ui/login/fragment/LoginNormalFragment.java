package com.example.kadh.ui.login.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragmentView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.login.activity.LoginForgetActivity;
import com.example.kadh.ui.login.presenter.LoginNormalBinding;
import com.example.kadh.ui.main.activity.MainActivity;
import com.example.kadh.ui.login.contract.LoginNormalFragContract;
import com.example.kadh.utils.NullUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginNormalFragment extends BaseFragmentView<LoginNormalBinding> implements LoginNormalFragContract.View {

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
        RxTextView.textChanges(mEtUsername).skipInitialValue().subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                mUsername = String.valueOf(charSequence);
                if (NullUtils.isEmpty(charSequence)) {
                    mBtnClear.setVisibility(View.INVISIBLE);
                } else {
                    mBtnClear.setVisibility(View.VISIBLE);
                }
            }
        });

        RxTextView.textChanges(mEtPassword).skipInitialValue().subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                mPasswrod = String.valueOf(charSequence);
            }
        });

        RxView.clicks(mBtnForget).throttleFirst(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                openActivity(LoginForgetActivity.class);
            }
        });

        RxView.clicks(mBtnFastlogin).throttleFirst(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Toast.makeText(mActivity, "快速登陆", Toast.LENGTH_SHORT).show();
                openActivity(MainActivity.class);
            }
        });

        RxView.clicks(mBtnEye).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                changePwdType();
            }
        });

        RxView.clicks(mBtnClear).throttleFirst(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mEtUsername.setText("");
            }
        });

        RxView.clicks(mBtnLogin).debounce(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {

            @Override
            public void accept(Object o) throws Exception {
                checkInput(mUsername, mPasswrod);
            }
        });
    }

    private void changePwdType() {
        if (mEtPassword.getInputType() == (InputType.TYPE_CLASS_TEXT)) {
            mBtnEye.setBackgroundResource(R.mipmap.pwd_eye_selected);
            mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            mBtnEye.setBackgroundResource(R.mipmap.pwd_eye_default);
            mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
        mEtPassword.setSelection(mEtPassword.getText().toString().length());
    }

    @Override
    protected void initDatas() {
        mPresenter.getLoginData();
        mPresenter.getFastData();
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
    public void setLoginData(String username, String passwrod) {
        if (!NullUtils.isEmpty(username)) {
            mUsername = username;
            mEtUsername.setText(username);
        }
        if (!NullUtils.isEmpty(passwrod)) {
            mPasswrod = passwrod;
            mEtPassword.setText(passwrod);
//            checkInput(username, passwrod);
        }
    }

    @Override
    public void checkInput(String username, String passwrod) {
        if (NullUtils.isEmpty(username) || NullUtils.isEmpty(passwrod)) {
            Toast.makeText(mContext, "账号或者密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.login(username, passwrod);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(mActivity, "登陆成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mContext, MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void loginFail() {
        Toast.makeText(mActivity, "服务器异常,请反馈给研发中心,谢谢!!", Toast.LENGTH_SHORT).show();
    }

}

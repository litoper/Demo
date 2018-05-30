package com.example.kadh.ui.login;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragment;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.SpUtil;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginNormalFragment extends BaseFragment {
    private static final String TAG = "LoginNormalFragment";

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
    Unbinder unbinder;
    private String mUsername;
    private String mPassword;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login_normal;
    }

    @Override
    protected void configViews() {


    }

    @Override
    protected void initDatas() {
        mUsername = SpUtil.getInstance().getString(SpUtil.LOGIN_USERNAME);
        mPassword = SpUtil.getInstance().getString(SpUtil.LOGIN_PASSWORD);
        mEtUsername.setText(NullUtils.filterNull(mUsername));
        mEtPassword.setText(NullUtils.filterNull(mPassword));

        Observable<CharSequence> valueUsername = RxTextView.textChanges(mEtUsername).skipInitialValue();
        Observable<CharSequence> valuePassword = RxTextView.textChanges(mEtPassword).skipInitialValue();

        Observable.
                combineLatest(
                        valueUsername,
                        valuePassword,
                        new BiFunction<CharSequence, CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                                Log.d(TAG, "apply() called with: charSequence = [" + charSequence + "], charSequence2 = [" + charSequence2 + "]");
                                return NullUtils.isNullOrEmpty(String.valueOf(charSequence)) && NullUtils.isNullOrEmpty(String.valueOf(charSequence2));
                            }
                        }).
                subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG, "accept() called with: aBoolean = [" + aBoolean + "]");
                        if (aBoolean) {

                        } else {
                            Log.d(TAG, "accept() called with: aBoolean = [" + aBoolean + "]");
                        }
                    }
                }).
                isDisposed();

    }

    @Override
    protected void attachView() {

    }

    @OnClick({R.id.login_normal_btn_clear, R.id.login_normal_et_username, R.id.login_normal_btn_eye, R.id.login_normal_et_password, R.id.login_normal_btn_login, R.id.login_normal_btn_fastlogin, R.id.login_normal_btn_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_normal_btn_clear:
                break;
            case R.id.login_normal_et_username:
                break;
            case R.id.login_normal_btn_eye:
                break;
            case R.id.login_normal_et_password:
                break;
            case R.id.login_normal_btn_login:
                break;
            case R.id.login_normal_btn_fastlogin:
                break;
            case R.id.login_normal_btn_forget:
                break;
        }
    }
}

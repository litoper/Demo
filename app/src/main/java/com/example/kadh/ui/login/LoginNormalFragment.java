package com.example.kadh.ui.login;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragment;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxSubscriber.SubOnNextImpl;
import com.example.kadh.utils.SpUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginNormalFragment extends BaseFragment implements LoginContract.Presenter {
    private static final String TAG = "88888888888";

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

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login_normal;
    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void initDatas() {
        String password = SpUtil.getInstance().getString(SpUtil.LOGIN_PASSWORD);
        String username = SpUtil.getInstance().getString(SpUtil.LOGIN_USERNAME);
        mEtPassword.setText(NullUtils.filterNull(password));
        mEtUsername.setText(NullUtils.filterNull(username));
        if (!NullUtils.isNull(username) && !NullUtils.isNull(password)) {
            processLogin();
        }
    }

    private void processLogin() {
        String username = mEtUsername.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        if (NullUtils.isEmpty(username)) {
            Toast.makeText(mContext, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        if (NullUtils.isEmpty(password)) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        SubOnNextImpl<BaseResponse<List<LoginModel>>> loginListener = new SubOnNextImpl<BaseResponse<List<LoginModel>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<LoginModel>> baseResponse) {
                List<LoginModel> loginModels = baseResponse.data;
                Log.d(TAG, "loginModels:" + loginModels);
                Toast.makeText(mContext, "loginModels:" + loginModels, Toast.LENGTH_SHORT).show();
            }
        };
//        RxApiManager.getRxApi().login(new SubProgress<BaseResponse<List<LoginModel>>>(loginListener, mContext, 0), username, password);
    }

    @Override
    public void attachView(LoginContract.View view) {

    }

    @Override
    public void detachView() {

    }
}

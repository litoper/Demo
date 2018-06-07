package com.example.kadh.ui.setting;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.service.pushreceiver.PushReceiverManager;
import com.example.kadh.ui.login.activity.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/6
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.activity_setting_rl_modifypwd)
    LinearLayout mRlModifypwd;
    @BindView(R.id.activity_setting_rl_function)
    LinearLayout mRlFunction;
    @BindView(R.id.person_tv_cachesize)
    TextView mPersonTvCachesize;
    @BindView(R.id.activity_setting_ll_clear)
    LinearLayout mLlClear;
    @BindView(R.id.activity_setting_rl_about)
    LinearLayout mRlAbout;
    @BindView(R.id.activity_setting_rl_share)
    LinearLayout mRlShare;
    @BindView(R.id.activity_setting_rl_description)
    LinearLayout mRlDescription;
    @BindView(R.id.activity_setting_rl_update)
    LinearLayout mRlUpdate;
    @BindView(R.id.activity_setting_btn_quit)
    Button mBtnQuit;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("设置");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @OnClick({R.id.activity_setting_rl_modifypwd, R.id.activity_setting_rl_function, R.id.activity_setting_ll_clear, R.id.activity_setting_rl_about, R.id.activity_setting_rl_share, R.id.activity_setting_rl_description, R.id.activity_setting_rl_update, R.id.activity_setting_btn_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_setting_rl_modifypwd:
                break;
            case R.id.activity_setting_rl_function:
                break;
            case R.id.activity_setting_ll_clear:
                break;
            case R.id.activity_setting_rl_about:
                break;
            case R.id.activity_setting_rl_share:
                break;
            case R.id.activity_setting_rl_description:
                break;
            case R.id.activity_setting_rl_update:
                break;
            case R.id.activity_setting_btn_quit:
                loginOut();
                break;
            default:
                break;
        }
    }

    private void loginOut() {
//        SpUtil.getInstance().remove(SpUtil.LOGIN_PASSWORD);
//        SpUtil.getInstance().remove(SpUtil.LOGIN_USERNAME);
        PushReceiverManager.getInstance().unsetAlias();
        openActivity(LoginActivity.class);
        finish();
    }
}

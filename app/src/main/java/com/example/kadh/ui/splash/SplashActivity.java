package com.example.kadh.ui.splash;

import android.support.design.widget.CoordinatorLayout;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.home.MainActivity;
import com.example.kadh.ui.login.activity.LoginActivity;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.socks.library.KLog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Cookie;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    @BindView(R.id.activity_welcomt_root)
    CoordinatorLayout mRoot;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        checkCookie();
    }

    public void checkCookie() {
        List<Cookie> cookies = RxManager.getInstant().getCookie().loadAll();
        KLog.d(cookies);
        if (cookies.isEmpty()) {
            openActivity(LoginActivity.class);
        } else {
            openActivity(MainActivity.class);
        }
        finish();
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public int getLayoutId() {
        hideStatusBar();
        return R.layout.activity_welcome;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }


    @OnClick(R.id.activity_welcomt_root)
    public void onViewClicked() {
    }
}

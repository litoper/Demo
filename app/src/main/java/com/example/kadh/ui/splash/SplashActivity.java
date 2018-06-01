package com.example.kadh.ui.splash;

import android.support.design.widget.CoordinatorLayout;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.login.activity.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
//        RxView.clicks(mRoot).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                openActivity(LoginActivity.class);
//            }
//        });

    }

    @Override
    public void initDatas() {
        openActivity(LoginActivity.class);
    }

    public void checkCookie() {
//        List<Cookie> cookies = RxApi.getRxApi().getCookiePersistor().loadAll();
//        if (cookies.isEmpty()) {
//            openActivity(LoginActivity.class);
//        } else {
//            openActivity(MianActivity.class);
//        }
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

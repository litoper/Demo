package com.example.kadh.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.kadh.R;
import com.example.kadh.app.App;
import com.example.kadh.component.AppComponent;
import com.example.kadh.utils.StatusBarCompat;

import butterknife.ButterKnife;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Toolbar mCommonToolbar;

    protected Context mContext;
    protected int statusBarColor = 0;
    protected View statusBarView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        if (statusBarColor == 0) {
            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        } else if (statusBarColor != -1) {
            statusBarView = StatusBarCompat.compat(this, statusBarColor);
        }
        transparent19and20();
        ButterKnife.bind(this);
        setupActivityComponent(App.getApp().getAppComponent());
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (mCommonToolbar != null) {
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }
        attachView();
        initDatas();
        configViews();
    }


    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    protected void attachView() {

    }

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();

    public abstract void initDatas();

    public abstract void initToolBar();

    public abstract int getLayoutId();

    protected abstract void setupActivityComponent(AppComponent appComponent);


    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }

    /**
     * 当无参数需要传递时，可调用单参的该函数
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 当无参数需要传递,但有返回结果，可向Bundle传递null
     */
    public void openActivity(Class<?> pClass, int requestCode) {
        openActivity(pClass, null, requestCode);
    }

    /**
     * 传递不带返回值的意图
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent i = new Intent(this, pClass);
        if (pBundle != null) {
            i.putExtras(pBundle);
        }
        startActivity(i);
    }

    /**
     * 传递带有返回值的activity，简化了意图的代码
     */
    public void openActivity(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent i = new Intent(this, pClass);
        if (pBundle != null) {
            i.putExtras(pBundle);
        }
        startActivityForResult(i, requestCode);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_left_to_middle, R.anim.anim_middle_to_right);//上一步
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
//            overridePendingTransition(R.anim.leftmove2, R.anim.rightmove2);//上一步
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

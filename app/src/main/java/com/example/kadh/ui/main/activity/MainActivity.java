package com.example.kadh.ui.main.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.main.adapter.SectionsPagerAdapter;
import com.example.kadh.ui.main.bean.IsHasUnReadBean;
import com.example.kadh.ui.main.bean.WeatherBean;
import com.example.kadh.ui.main.contract.MainAtyContract;
import com.example.kadh.ui.main.presenter.MainPresenter;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class MainActivity extends BaseActivity implements MainAtyContract.View {
    @BindView(R.id.activity_main_vp)
    ViewPager mVp;
    @BindView(R.id.activity_main_bnb)
    BottomNavigationBar mBottomNavigationBar;

    @Inject
    MainPresenter mPresenter;
    private long mExittime;
    private ShapeBadgeItem mBadgeItem;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        mPresenter.atachView(this);
        mPresenter.initViewPager();
        mPresenter.initBottomNavigationBar();
        mPresenter.initSubListener();
        this.checkPermission();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("标题栏");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }


    @Override
    public void showViewPager(List<Fragment> fragmentList) {
        mVp.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragmentList));
        mVp.setCurrentItem(0);

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mBottomNavigationBar.selectTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void showBottomNavigationBar() {
        mBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor("#FF6960")
                .setSizeInDp(this, 5, 5)
                .setHideOnSelect(true);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon_gongsi_b, "公司")
                        .setInactiveIcon(getResources().getDrawable(R.drawable.bottom_icon_gongsi)))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon_work_b, "工作")
                        .setInactiveIcon(getResources().getDrawable(R.drawable.bottom_icon_work))
                        .setBadgeItem(mBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon_tongxunlu_b, "通讯录")
                        .setInactiveIcon(getResources().getDrawable(R.drawable.botton_icon_tongxunlu)))
                .addItem(new BottomNavigationItem(R.drawable.bottom_icon_wode_b, "我的")
                        .setInactiveIcon(getResources().getDrawable(R.drawable.bottom_icon_wode)))
                .setActiveColor(R.color.blue)
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int i) {
                mVp.setCurrentItem(i);
            }

            @Override
            public void onTabUnselected(int i) {

            }

            @Override
            public void onTabReselected(int i) {

            }
        });
    }

    @Override
    public void showBadge(IsHasUnReadBean unReadBean) {
        switch (unReadBean.getWaitDealState()) {
            case "1":
                mBadgeItem.show();
                break;
            case "0":
                mBadgeItem.hide();
                break;
            default:
                break;
        }
    }

    @Override
    public void showWeather(WeatherBean weatherBean) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void checkPermission() {
        new RxPermissions(this)
                .requestEach(
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // TODO: 2018/6/4
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // TODO: 2018/6/4
                        } else {
                            // TODO: 2018/6/4
                        }
                    }
                });


    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if ((System.currentTimeMillis() - mExittime) > 2000) {
//                Toast.makeText(getApplicationContext(), "再按一次退出程序！",
//                        Toast.LENGTH_SHORT).show();
//                mExittime = System.currentTimeMillis();
//            } else {
//                System.exit(0);
//            }

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

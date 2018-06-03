package com.example.kadh.ui.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.main.adapter.SectionsPagerAdapter;
import com.example.kadh.ui.main.contract.MainAtyContract;
import com.example.kadh.ui.main.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class MainActivity extends BaseActivity implements MainAtyContract.View {
    @BindView(R.id.common_toolbar)
    Toolbar             mCommonToolbar;
    @BindView(R.id.activity_main_vp)
    ViewPager           mVp;
    @BindView(R.id.activity_main_bnb)
    BottomNavigationBar mBottomNavigationBar;
    private TextBadgeItem mBadgeItem;

    @Inject
    MainPresenter mPresenter;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        mPresenter.initViewPager();
        mPresenter.initBottomNavigationBar();
        mPresenter.getUseInfo();
        mPresenter.getUserConfigInfo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).activityComponent(mActivityComponent).build().inject(this);
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
        mBadgeItem = new TextBadgeItem().setText("88").setTextColor(R.color.red).setHideOnSelect(true);
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
}

package com.example.kadh.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class MianActivity extends BaseActivity {
    @BindView(R.id.common_toolbar)
    Toolbar              mCommonToolbar;
    @BindView(R.id.activity_main_vp)
    ViewPager            mVp;
    @BindView(R.id.activity_main_bnb)
    BottomNavigationBar  mBnb;
    @BindView(R.id.activity_main_fabtn)
    FloatingActionButton mFaBtn;
    private TextBadgeItem mBadgeItem;

    @Override
    public void configViews() {
        initBottomNavigationBar();
        initViewPager();
        initFaBtn();
    }

    private void initFaBtn() {
        mFaBtn.show();
    }

    private void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        MainFragment frgCompany = new MainFragment("公司");
        MainFragment frgWork = new MainFragment("工作");
        MainFragment frgTxl = new MainFragment("通讯录");
        MainFragment frgMy = new MainFragment("我的");

        fragments.add(frgCompany);
        fragments.add(frgWork);
        fragments.add(frgTxl);
        fragments.add(frgMy);

        mVp.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        mVp.setCurrentItem(0);

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mBnb.selectTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void initBottomNavigationBar() {
        mBadgeItem = new TextBadgeItem().setText("88").setTextColor(R.color.red).setHideOnSelect(true);
        mBnb
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

        mBnb.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
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
    public void initDatas() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.common_toolbar, R.id.activity_main_vp, R.id.activity_main_fabtn, R.id.activity_main_bnb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_toolbar:
                break;
            case R.id.activity_main_vp:
                break;
            case R.id.activity_main_fabtn:
                Toast.makeText(this, "fabtn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_bnb:
                break;
        }
    }
}

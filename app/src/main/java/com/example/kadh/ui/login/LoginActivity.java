package com.example.kadh.ui.login;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.base.TabAdapter;
import com.example.kadh.component.AppComponent;
import com.example.kadh.view.RippleBackground;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/30
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.activity_login_rbg)
    RippleBackground mRbg;
    @BindView(R.id.activity_login_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.activity_login_viewpager)
    ViewPager mViewpager;
    private LoginNormalFragment mLoginNormalFragment;
    private LoginVerifyFragment mLoginVerifyFragment;
    private ArrayList<String> mListTitle;
    private ArrayList<Fragment> mListFragment;
    private TabAdapter mTabAdapter;


    @Override
    public void configViews() {
        mRbg.startRippleAnimation();
    }

    @Override
    public void initDatas() {

        mLoginNormalFragment = new LoginNormalFragment();
        mLoginVerifyFragment = new LoginVerifyFragment();
        mListFragment = new ArrayList<>();
        mListFragment.add(mLoginNormalFragment);
        mListFragment.add(mLoginVerifyFragment);

        mListTitle = new ArrayList<>();
        mListTitle.add("登陆");
        mListTitle.add("验证码登陆");
        //设置适配器
        mTabAdapter = new TabAdapter(getSupportFragmentManager(), mListFragment, mListTitle);
        mViewpager.setAdapter(mTabAdapter);
        //设置选项卡的模式
        mTablayout.setTabMode(TabLayout.MODE_FIXED);//MODE_FIXED代表tab不能滚动，平分屏幕宽度
        //MODE_SCROLLABLE代表tab可以滚动，tab宽度根据内容自动缩放
        mTablayout.setupWithViewPager(mViewpager);//让选项卡与ViewPager联动,这步一定要在setAdapter方法之后

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public int getLayoutId() {
        hideStatusBar();
        return R.layout.activity_login;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}

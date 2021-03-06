package com.example.kadh.ui.login.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.base.BaseTabAdapter;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.login.fragment.LoginNormalFragment;
import com.example.kadh.ui.login.fragment.LoginVerifyFragment;
import com.example.kadh.view.RippleBackground;

import java.util.ArrayList;

import butterknife.BindView;

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
    private ArrayList<String> mListTitle;
    private ArrayList<Fragment> mListFragment;
    private BaseTabAdapter mTabAdapter;


    @Override
    public void configViews() {
        mRbg.startRippleAnimation();
    }

    @Override
    public void initDatas() {

        mListFragment = new ArrayList<>();
        mListFragment.add(LoginNormalFragment.newInstance());
        mListFragment.add(LoginVerifyFragment.newInstance());

        mListTitle = new ArrayList<>();
        mListTitle.add("登陆");
        mListTitle.add("验证码登陆");
        //设置适配器
        mTabAdapter = new BaseTabAdapter(getSupportFragmentManager(), mListFragment, mListTitle);
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
}

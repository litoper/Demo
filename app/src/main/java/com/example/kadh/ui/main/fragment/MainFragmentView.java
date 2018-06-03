package com.example.kadh.ui.main.fragment;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragmentView;
import com.example.kadh.component.AppComponent;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class MainFragmentView extends BaseFragmentView {


    @BindView(R.id.fragment_mian_tv)
    TextView mFragmentMianTv;
    private String mTitle;

    public MainFragmentView() {
    }

    @SuppressLint("ValidFragment")
    public MainFragmentView(String title) {
        mTitle = title;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void configViews() {
        mFragmentMianTv.setText(mTitle);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


}

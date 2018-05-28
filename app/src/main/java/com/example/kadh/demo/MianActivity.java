package com.example.kadh.demo;

import android.view.Menu;

import com.example.kadh.base.BaseActivity;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class MianActivity extends BaseActivity {
    @Override
    public void configViews() {

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
    public void initDatas() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

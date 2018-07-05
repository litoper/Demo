package com.example.kadh.ui.work.activity;

import android.os.Bundle;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class BasePendingDealActivity extends BaseActivity {

    private Bundle mAllowid;
    private Bundle mPtype;
    private Bundle mId;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {


    }

    @Override
    public void initToolBar() {
        mId = getIntent().getBundleExtra("id");
        mPtype = getIntent().getBundleExtra("ptype");
        mAllowid = getIntent().getBundleExtra("allowId");
        mCommonToolbar.setTitle("流程审批");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_pending_deal_base;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}

package com.example.kadh.ui.tension.activity;

import android.widget.Button;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivityView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.tension.contract.TensionAtyContract;
import com.example.kadh.ui.tension.presenter.TensionBinding;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/21
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class TensionActivity extends BaseActivityView<TensionBinding> implements TensionAtyContract.View {
    @BindView(R.id.activity_tension_btn)
    Button mBtn;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
        mCommonToolbar.setTitle("我的张力");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tension;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @OnClick(R.id.activity_tension_btn)
    public void onViewClicked() {
        openActivity(TensionSubmitActivity.class);
    }
}

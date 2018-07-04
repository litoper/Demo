package com.example.kadh.ui.person.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kadh.R;
import com.example.kadh.base.BaseFragment;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.person.activity.HelpActivity;
import com.example.kadh.ui.person.activity.PersonInfoActivity;
import com.example.kadh.ui.person.bean.QueryProTotalInfoBean;
import com.example.kadh.ui.setting.SettingActivity;
import com.example.kadh.utils.GlideUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxApi.RxUrl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;
import com.example.kadh.utils.SpUtil;
import com.example.kadh.view.CircleImageView.CircleImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonFragment extends BaseFragment {

    @BindView(R.id.person_civ_personal)
    CircleImageView mCivPersonal;
    @BindView(R.id.person_tv_name)
    TextView mTvName;
    @BindView(R.id.person_tv_overtime)
    TextView mTvOvertime;
    @BindView(R.id.person_tv_leavetime)
    TextView mTvLeavetime;
    @BindView(R.id.person_ll_attence)
    LinearLayout mLlAttence;
    @BindView(R.id.person_ll_company)
    LinearLayout mLlCompany;
    @BindView(R.id.person_ll_help)
    LinearLayout mLlHelp;
    @BindView(R.id.person_ll_setting)
    LinearLayout mLlSetting;
    @BindView(R.id.person_smart_refresh)
    SmartRefreshLayout mSmartRefresh;

    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

    public PersonFragment() {
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main_person;
    }

    @Override
    protected void configViews() {
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getTimeInfo();
            }
        });
    }

    @Override
    protected void initDatas() {
        setUserInfo();
        getTimeInfo();
    }


    private void getTimeInfo() {
        RxManager.getInstant().getRxApi().queryProcessBaseTotalInfo(new SubProtect<BaseResponse<QueryProTotalInfoBean>>(new SubNextImpl<BaseResponse<QueryProTotalInfoBean>>() {
            @Override
            public void onSubSuccess(BaseResponse<QueryProTotalInfoBean> response) {
                mTvOvertime.setText(setTextBigOrSmall(response.data.getPro_jiaban()));
                mTvLeavetime.setText(setTextBigOrSmall(response.data.getPro_qingjia()));
                mSmartRefresh.finishRefresh();
            }
        }));
    }

    /**
     * 设置不一样的字体大小
     *
     * @param text
     * @return
     */
    private SpannableString setTextBigOrSmall(String text) {
        String time = text + " h";
        int index = time.indexOf('h');
        SpannableString textSpan = new SpannableString(time);
        textSpan.setSpan(new AbsoluteSizeSpan(40), index, index + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return textSpan;
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    private void setUserInfo() {
        String userIcon = SpUtil.getInstance().getString(SpUtil.LOGIN_INFO_USERICO);
        String userName = SpUtil.getInstance().getString(SpUtil.LOGIN_INFO_USERNAME);
        mTvName.setText(userName);
        GlideUtils.loadImageView(mContext, RxUrl.Url.BASE + userIcon, mCivPersonal);
    }

    @OnClick({R.id.person_civ_personal, R.id.person_ll_attence, R.id.person_ll_company, R.id.person_ll_help, R.id.person_ll_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_civ_personal://进入个人中心
                openActivity(PersonInfoActivity.class, 888);
                break;
            case R.id.person_ll_attence://考勤记录
//                openActivity(AttenceActivity.class);
                break;
            case R.id.person_ll_company://企业文化
//                openActivity(CultureActivity.class);
                break;
            case R.id.person_ll_help://帮助与反馈页面
                openActivity(HelpActivity.class);
                break;
            case R.id.person_ll_setting://进入设置页面
                openActivity(SettingActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 888) {
                setUserInfo();
            }
        }
    }
}

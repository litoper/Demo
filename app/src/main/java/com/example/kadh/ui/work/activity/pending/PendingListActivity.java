package com.example.kadh.ui.work.activity.pending;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.base.BaseTabAdapter;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.work.fragment.pending.PendingAllFragment;
import com.example.kadh.ui.work.fragment.pending.PendingCoreValueFragment;
import com.example.kadh.ui.work.fragment.pending.PendingGoalFragment;
import com.example.kadh.ui.work.fragment.pending.PendingMeetingFragment;
import com.example.kadh.ui.work.fragment.pending.PendingNoticeFragment;
import com.example.kadh.ui.work.fragment.pending.PendingProcessFragment;
import com.example.kadh.ui.work.fragment.pending.PendingTensionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PendingListActivity extends BaseActivity {
    @BindView(R.id.activity_work_process_pending_list_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.activity_work_process_pending_list_viewpager)
    ViewPager mViewpager;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        List<String> listTitle = new ArrayList<>();
        listTitle.add("全部");
        listTitle.add("流程");
        listTitle.add("会议");
        listTitle.add("知会");
        listTitle.add("核心价值观");
        listTitle.add("张力");
        listTitle.add("目标");

        //    private String MSG_ALL = "0";//查询全部
        //    private String MSG_PROCESS = "3";//查询流程
        //    private String MSG_MEETING = "4";//查询会议
        //    private String MSG_NOTICE = "5";//查询知会
        //    private String MSG_COREVALUE = "6";//查询核心价值观
        //    private String MSG_TENSION = "7";//查询张力
        //    private String MSG_GOAL = "8";//查询目标
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(PendingAllFragment.newInstance());
        listFragment.add(PendingProcessFragment.newInstance());
        listFragment.add(PendingMeetingFragment.newInstance());
        listFragment.add(PendingNoticeFragment.newInstance());
        listFragment.add(PendingCoreValueFragment.newInstance());
        listFragment.add(PendingTensionFragment.newInstance());
        listFragment.add(PendingGoalFragment.newInstance());

        BaseTabAdapter tabAdapter = new BaseTabAdapter(getSupportFragmentManager(), listFragment, listTitle);
        mViewpager.setAdapter(tabAdapter);
        mTablayout.setupWithViewPager(mViewpager);

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("待办");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_process_pending;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}

package com.example.kadh.ui.work.fragment.pending;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.ui.work.bean.BacklogListBean;
import com.example.kadh.ui.work.fragment.BasePendingFragment;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PendingGoalFragment extends BasePendingFragment {
    public static PendingGoalFragment newInstance() {
        return new PendingGoalFragment();
    }

    public PendingGoalFragment() {
        mType = "8";
    }

    @Override
    public void onAdapterItemClick(BaseQuickAdapter adapter, View view, int position, BacklogListBean backlogListBean) {
        Bundle bundle = new Bundle();
        bundle.putString("allowid", backlogListBean.getAllowid());
        bundle.putString("meetingId", backlogListBean.getId());
        bundle.putString("ptype", backlogListBean.getPtype());
        bundle.putString("type", backlogListBean.getType());
        bundle.putString("status", backlogListBean.getPstatus());
        bundle.putString("markNode", backlogListBean.getMarkNode());
        switch (backlogListBean.getType()) {
            case "3"://目标详情页
                break;
            default://空白页面
                break;
        }
        Toast.makeText(mContext, "日目标处理页面待添加", Toast.LENGTH_SHORT).show();
    }
}

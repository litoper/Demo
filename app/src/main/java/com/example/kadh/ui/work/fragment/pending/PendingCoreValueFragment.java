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
public class PendingCoreValueFragment extends BasePendingFragment {

    public static PendingCoreValueFragment newInstance() {
        return new PendingCoreValueFragment();
    }

    public PendingCoreValueFragment() {
        mType = "6";
    }

    @Override
    public void onAdapterItemClick(BaseQuickAdapter adapter, View view, int position, BacklogListBean backlogListBean) {
        Bundle bundle = new Bundle();
        bundle.putString("allowid", backlogListBean.getAllowid());
        bundle.putString("meetingId", backlogListBean.getId());
        bundle.putString("ptype", backlogListBean.getPtype());
        Toast.makeText(mContext, "核心价值观处理页面待添加", Toast.LENGTH_SHORT).show();
    }
}

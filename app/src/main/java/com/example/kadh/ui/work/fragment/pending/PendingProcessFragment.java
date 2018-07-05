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
public class PendingProcessFragment extends BasePendingFragment {

    public static PendingProcessFragment newInstance() {
        return new PendingProcessFragment();
    }

    public PendingProcessFragment() {
        mType = "3";
    }

    @Override
    public void onAdapterItemClick(BaseQuickAdapter adapter, View view, int position, BacklogListBean backlogListBean) {
        Bundle bundle = new Bundle();
        String allowid = backlogListBean.getAllowid();
        String id = backlogListBean.getId();
        bundle.putString("allowId", allowid);
        bundle.putString("id", id);

        Toast.makeText(mContext, "流程处理页面待添加", Toast.LENGTH_SHORT).show();
    }
}

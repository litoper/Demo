package com.example.kadh.ui.work.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.work.bean.ProcessListBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/26
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessListAdapter extends BaseQuickAdapter<ProcessListBean, BaseViewHolder> {
    public ProcessListAdapter(int layoutResId, @Nullable List<ProcessListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProcessListBean item) {
        helper.setText(R.id.item_work_process_list_tv_group, item.getPname());
    }
}

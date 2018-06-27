package com.example.kadh.ui.work.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.work.bean.ProcessContentBean;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/27
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessSubmitAdapter extends BaseMultiItemQuickAdapter<ProcessContentBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ProcessSubmitAdapter(List<ProcessContentBean> data) {
        super(data);
        addItemType(ProcessContentBean.NORMAL, R.layout.item_process_submit_normal);
        addItemType(ProcessContentBean.TEXT, R.layout.item_process_submit_text);
        addItemType(ProcessContentBean.MTEXT, R.layout.item_process_submit_mtext);
        addItemType(ProcessContentBean.ATT, R.layout.item_process_submit_att);
        addItemType(ProcessContentBean.PHOTO, R.layout.item_process_submit_photo);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProcessContentBean item) {

        switch (helper.getItemViewType()) {
            case ProcessContentBean.NORMAL:
                break;
            case ProcessContentBean.MTEXT:
                break;
            case ProcessContentBean.ATT:
                break;
            case ProcessContentBean.PHOTO:
                break;
            case ProcessContentBean.TEXT:
                break;
            default:
                break;
        }
    }
}


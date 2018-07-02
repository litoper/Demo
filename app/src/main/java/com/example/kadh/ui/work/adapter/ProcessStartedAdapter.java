package com.example.kadh.ui.work.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseViewHolderImpl;
import com.example.kadh.ui.work.bean.ProcessAlReadyBean;
import com.example.kadh.utils.NullUtils;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/2
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessStartedAdapter extends BaseQuickAdapter<ProcessAlReadyBean, BaseViewHolderImpl> {
    public ProcessStartedAdapter(int layoutResId, @Nullable List<ProcessAlReadyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolderImpl helper, ProcessAlReadyBean item) {
        helper.setText(R.id.item_process_started_tv_title, NullUtils.filterNull(item.getPtitle()));
        helper.setText(R.id.item_process_started_tv_time, NullUtils.filterEmpty(item.getPcreate_time()).replace("-", "/").trim());

        switch (NullUtils.filterEmpty(item.getPrejected())) {
            case "0"://默认
                helper.setText(R.id.item_process_started_tv_status, "审核中");
                helper.setTextColor(R.id.item_process_started_tv_status, Color.parseColor("#B3C9DB"));
                helper.setBackgroundRes(R.id.item_process_started_tv_status, R.drawable.btn_process_started_status_pending);
                break;
            case "1"://已通过
                helper.setText(R.id.item_process_started_tv_status, "已同意");
                helper.setTextColor(R.id.item_process_started_tv_status, Color.parseColor("#1ABC9C"));
                helper.setBackgroundRes(R.id.item_process_started_tv_status, R.drawable.btn_process_started_status_agree);
                break;
            case "2"://未通过
                helper.setText(R.id.item_process_started_tv_status, "被驳回");
                helper.setTextColor(R.id.item_process_started_tv_status, Color.parseColor("#e74c3c"));
                helper.setBackgroundRes(R.id.item_process_started_tv_status, R.drawable.btn_process_started_status_reject);
                break;
            default:
                break;
        }
    }
}

package com.example.kadh.ui.work.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseViewHolderImpl;
import com.example.kadh.ui.work.bean.BacklogListBean;
import com.example.kadh.utils.NullUtils;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class ProcessPendingSingleAdapter extends BaseQuickAdapter<BacklogListBean, BaseViewHolderImpl> {
    public ProcessPendingSingleAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolderImpl helper, BacklogListBean item) {
        helper.setVisible(R.id.item_process_pending_single_iv_readed, !"1".equals(item.getPreaded()));
        helper.setText(R.id.item_process_pending_single_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        helper.setText(R.id.item_process_pending_single_tv_person, NullUtils.filterEmpty(item.getPsponsor()));
        helper.setText(R.id.item_process_pending_single_tv_time, NullUtils.filterEmpty(item.getCreate_time()));

        if ("4".equals(item.getPtype())) {
            helper.setVisible(R.id.item_process_pending_single_btn_status, true);
            //会议状态(0:待确认 3：未签到 4.未签退)
            switch (item.getPstatus()) {
                case "0":
                    helper.setText(R.id.item_process_pending_single_btn_status, "待确认");
                    break;
                case "3":
                    helper.setText(R.id.item_process_pending_single_btn_status, "未签到");
                    break;
                case "4":
                    helper.setText(R.id.item_process_pending_single_btn_status, "未签退");
                    break;
                default:
                    break;
            }
        } else if ("7".equals(item.getPtype())) {
            helper.setVisible(R.id.item_process_pending_single_btn_status, true);
            switch (item.getMarkNode()) {
                case "0"://正常张力
                    helper.setText(R.id.item_process_pending_single_btn_status, "待处理");
                    break;
                case "1"://价值整理
                    helper.setText(R.id.item_process_pending_single_btn_status, "待审核");
                    break;
                default:
                    break;
            }
        } else {
            helper.setVisible(R.id.item_process_pending_single_btn_status, false);
        }


        if (!NullUtils.isNull(item.getPtitle_remark()) && !"未填写".equals(item.getPtitle_remark()) && !"7".equals(item.getPtype()
        )) {
            helper.setVisible(R.id.item_process_pending_single_tv_remark, true);
            helper.setText(R.id.item_process_pending_single_tv_remark, " - " + item.getPtitle_remark());
        } else {
            helper.setVisible(R.id.item_process_pending_single_tv_remark, false);
        }

    }
}

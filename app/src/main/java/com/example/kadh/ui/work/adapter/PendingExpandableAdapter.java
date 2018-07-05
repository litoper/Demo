package com.example.kadh.ui.work.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.kadh.R;
import com.example.kadh.base.BaseViewHolderImpl;
import com.example.kadh.ui.work.bean.BacklogListBean;
import com.example.kadh.ui.work.bean.BacklogListHeaderBean;
import com.example.kadh.utils.NullUtils;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PendingExpandableAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolderImpl> implements BaseQuickAdapter.OnItemClickListener {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public PendingExpandableAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.item_process_pending_header);
        addItemType(1, R.layout.item_process_pending_child);
    }


    @Override
    protected void convert(final BaseViewHolderImpl helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case 0:
                final BacklogListHeaderBean bean = (BacklogListHeaderBean) item;
                helper.setText(R.id.item_process_pending_header_tv_count, String.valueOf(bean.getSize()));
                helper.setText(R.id.item_process_pending_header_tv_title, bean.getTitle());

                switch (bean.getTitle()) {
                    case "流程":
                        helper.setBackgroundRes(R.id.item_process_pending_header_iv_icon, R.mipmap.work_icon_work);
                        break;
                    case "会议":
                        helper.setBackgroundRes(R.id.item_process_pending_header_iv_icon, R.mipmap.work_icon_justice);
                        break;
                    case "知会":
                        helper.setBackgroundRes(R.id.item_process_pending_header_iv_icon, R.mipmap.work_icon_purchase);
                        break;
                    case "核心价值观":
                        helper.setBackgroundRes(R.id.item_process_pending_header_iv_icon, R.mipmap.work_icon_medal);
                        break;
                    case "张力":
                        helper.setBackgroundRes(R.id.item_process_pending_header_iv_icon, R.mipmap.pending_icon_problem);
                        break;
                    case "目标":
                        helper.setBackgroundRes(R.id.item_process_pending_header_iv_icon, R.mipmap.extend_icon_task);
                        break;
                    default:
                        break;
                }

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bean.isExpanded()) {
                            collapse(helper.getAdapterPosition());
                        } else {
                            expand(helper.getAdapterPosition());
                        }
                    }
                });
                break;
            case 1:
                BacklogListBean subItem = (BacklogListBean) item;

                helper.setVisible(R.id.item_process_pending_single_iv_readed, !"1".equals(subItem.getPreaded()));
                helper.setText(R.id.item_process_pending_single_tv_title, NullUtils.filterEmpty(subItem.getPtitle()));
                helper.setText(R.id.item_process_pending_single_tv_person, NullUtils.filterEmpty(subItem.getPsponsor()));
                helper.setText(R.id.item_process_pending_single_tv_time, NullUtils.filterEmpty(subItem.getCreate_time()));

                if ("4".equals(subItem.getPtype())) {
                    helper.setVisible(R.id.item_process_pending_single_btn_status, true);
                    //会议状态(0:待确认 3：未签到 4.未签退)
                    switch (subItem.getPstatus()) {
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
                } else if ("7".equals(subItem.getPtype())) {
                    helper.setVisible(R.id.item_process_pending_single_btn_status, true);
                    switch (subItem.getMarkNode()) {
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

                if (!NullUtils.isNull(subItem.getPtitle_remark()) && !"未填写".equals(subItem.getPtitle_remark()) && !"7".equals(subItem.getPtype())) {
                    helper.setVisible(R.id.item_process_pending_single_tv_remark, true);
                    helper.setText(R.id.item_process_pending_single_tv_remark, " - " + subItem.getPtitle_remark());
                } else {
                    helper.setVisible(R.id.item_process_pending_single_tv_remark, false);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // TODO: 2018/7/5 子项点击事件
    }
}

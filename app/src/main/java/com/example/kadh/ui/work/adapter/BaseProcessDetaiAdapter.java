package com.example.kadh.ui.work.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseViewHolderImpl;
import com.example.kadh.ui.work.bean.ProcessDetailedBean;
import com.example.kadh.utils.NullUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public abstract class BaseProcessDetaiAdapter extends BaseMultiItemQuickAdapter<ProcessDetailedBean.PthingModel, BaseViewHolderImpl> {

    private ProcessDetailedBean mProcessDetailedBean;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseProcessDetaiAdapter(List<ProcessDetailedBean.PthingModel> data, ProcessDetailedBean processDetailedBean) {
        super(data);
        mProcessDetailedBean = processDetailedBean;
        addItemType(ProcessDetailedBean.NORMAL, R.layout.item_process_deal_normal);
        addItemType(ProcessDetailedBean.TEXT, R.layout.item_process_deal_text);
        addItemType(ProcessDetailedBean.PHOTO, R.layout.item_process_deal_photo);
        addItemType(ProcessDetailedBean.ATT, R.layout.item_process_deal_att);
        addItemType(ProcessDetailedBean.URL_, R.layout.item_process_deal_url);
        addItemType(ProcessDetailedBean.SPACE, R.layout.item_process_deal_space);
        addItemType(ProcessDetailedBean.NOTIFY, R.layout.item_process_deal_notify);
        addItemType(ProcessDetailedBean.TENSION, R.layout.item_process_deal_tension);
        addItemType(ProcessDetailedBean.EVALUATE, R.layout.item_process_deal_evaluate);
        addItemType(ProcessDetailedBean.APPEAL, R.layout.item_process_deal_appeal);
        addItemType(ProcessDetailedBean.UPGRADE, R.layout.item_process_deal_upgrade);
    }


    @Override
    protected void convert(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        switch (item.getItemType()) {
            case ProcessDetailedBean.NORMAL:
                logicNormal(helper, item);
                break;
            case ProcessDetailedBean.TEXT:
                logicText(helper, item);
                break;
            case ProcessDetailedBean.PHOTO:
                logicPhoto(helper, item);
                break;
            case ProcessDetailedBean.ATT:
                logicAtt(helper, item);
                break;
            case ProcessDetailedBean.URL_:
                logicUrl(helper, item);
                break;
            case ProcessDetailedBean.SPACE:
                logicSpace(helper, item);
                break;
            case ProcessDetailedBean.NOTIFY:
                logicNotify(helper, item);
                break;
            case ProcessDetailedBean.TENSION:
                logicTension(helper, item);
                break;
            case ProcessDetailedBean.EVALUATE:
                logicEvaluate(helper, item);
                break;
            case ProcessDetailedBean.APPEAL:
                logicAppeal(helper, item);
                break;
            case ProcessDetailedBean.UPGRADE:
                logicUpgrade(helper, item);
                break;
            default:
                break;
        }
        if (getData().size() - 1 == helper.getAdapterPosition()) {
            setSteal(helper, item);
        }
    }

    public abstract void setSteal(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item);

    private void logicUpgrade(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_upgrade_tv_title, String.valueOf(item.getContext()));
    }

    private void logicAppeal(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_appeal_tv_title, String.valueOf(item.getContext()));
    }

    private void logicEvaluate(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
//        ScaleRatingBar srb = helper.getView(R.id.item_process_deal_evaluate_srb);
//        srb.setRating(Integer.parseInt(String.valueOf(item.getContext())));
    }

    private void logicTension(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_tension_tv_content, item.getTitle() + ":" + String.valueOf(item.getContext()));
    }

    private void logicNotify(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        // TODO: 2018/7/5 通知item待添加
    }

    private void logicSpace(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_space_tv_content, NullUtils.filterEmpty(item.getTitle()));
        helper.setVisible(R.id.item_process_deal_space_rl_blank, NullUtils.isEmpty(item.getTitle()));
        helper.setVisible(R.id.item_process_deal_space_ll_root, !NullUtils.isEmpty(item.getTitle()));

    }

    private void logicAtt(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_att_tv_title, NullUtils.filterEmpty(item.getTitle()));
    }

    private void logicPhoto(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_photo_tv_title, NullUtils.filterEmpty(item.getTitle()));
        // TODO: 2018/7/5 照片item待添加
    }

    private void logicUrl(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_url_tv_title, NullUtils.filterEmpty(item.getTitle()));
        helper.setText(R.id.item_process_deal_url_tv_player, NullUtils.filterEmpty(String.valueOf(item.getContext())));
    }

    private void logicText(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_text_tv_title, NullUtils.filterEmpty(item.getTitle()));
        helper.setText(R.id.item_process_deal_text_tv_value, NullUtils.filterEmpty(String.valueOf(item.getContext())));
    }

    private void logicNormal(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_normal_tv_title, NullUtils.filterEmpty(item.getTitle()));
        if (item.getTitle().contains("金额")) {
            try {
                NumberFormat instance = NumberFormat.getInstance(Locale.CHINA);
                helper.setText(R.id.item_process_deal_normal_tv_player, String.valueOf(instance.format(Double.parseDouble(String.valueOf(item.getContext())))));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            helper.setText(R.id.item_process_deal_normal_tv_player, NullUtils.filterEmpty(String.valueOf(item.getContext())));
        }
    }
}
//    UNDEFINE = -1;//未定义
//    NORMAL = 0;//普通
//    TEXT = 1;//多文本
//    PHOTO = 2;//图片
//    ATT = 3;//附件
//    URL_ = 4;//URL
//    SPACE = 5;//间距
//    NOTIFY = 6;//知会人
//    TENSION = 7;//张力责任部门、责任人、完成时间
//    EVALUATE = 8;//张力评分
//    APPEAL = 9;//申诉
//    UPGRADE = 10;//升级
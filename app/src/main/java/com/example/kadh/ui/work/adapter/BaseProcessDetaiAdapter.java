package com.example.kadh.ui.work.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.kadh.R;
import com.example.kadh.base.BaseViewHolderImpl;
import com.example.kadh.ui.work.bean.ProcessDetailedBean;
import com.example.kadh.utils.NullUtils;
import com.willy.ratingbar.ScaleRatingBar;

import java.text.NumberFormat;
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

    public BaseProcessDetaiAdapter(ProcessDetailedBean processDetailedBean) {
        super(processDetailedBean.getPthing());
        mProcessDetailedBean = processDetailedBean;
        addItemType(R.layout.item_process_deal_normal, ProcessDetailedBean.PthingModel.NORMAL);
        addItemType(R.layout.item_process_deal_text, ProcessDetailedBean.PthingModel.TEXT);
        addItemType(R.layout.item_process_deal_photo, ProcessDetailedBean.PthingModel.PHOTO);
        addItemType(R.layout.item_process_deal_att, ProcessDetailedBean.PthingModel.ATT);
        addItemType(R.layout.item_process_deal_url, ProcessDetailedBean.PthingModel.URL_);
        addItemType(R.layout.item_process_deal_space, ProcessDetailedBean.PthingModel.SPACE);
        addItemType(R.layout.item_process_deal_notify, ProcessDetailedBean.PthingModel.NOTIFY);
        addItemType(R.layout.item_process_deal_tension, ProcessDetailedBean.PthingModel.TENSION);
        addItemType(R.layout.item_process_deal_evaluate, ProcessDetailedBean.PthingModel.EVALUATE);
        addItemType(R.layout.item_process_deal_appeal, ProcessDetailedBean.PthingModel.APPEAL);
        addItemType(R.layout.item_process_deal_upgrade, ProcessDetailedBean.PthingModel.UPGRADE);
    }


    @Override
    protected void convert(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        switch (helper.getItemViewType()) {
            case ProcessDetailedBean.PthingModel.NORMAL:
                logicNormal(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.TEXT:
                logicText(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.PHOTO:
                logicPhoto(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.ATT:
                logicAtt(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.URL_:
                logicUrl(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.SPACE:
                logicSpace(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.NOTIFY:
                logicNotify(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.TENSION:
                logicTension(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.EVALUATE:
                logicEvaluate(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.APPEAL:
                logicAppeal(helper, item);
                break;
            case ProcessDetailedBean.PthingModel.UPGRADE:
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
        ScaleRatingBar srb = helper.getView(R.id.item_process_deal_evaluate_srb);
        srb.setRating(Integer.parseInt(String.valueOf(item.getContext())));
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
        helper.addOnClickListener(R.id.item_process_deal_url_tv_player);
    }

    private void logicText(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_normal_tv_title, NullUtils.filterEmpty(item.getTitle()));
        helper.setText(R.id.item_process_deal_normal_tv_player, NullUtils.filterEmpty(String.valueOf(item.getContext())));
    }

    private void logicNormal(BaseViewHolderImpl helper, ProcessDetailedBean.PthingModel item) {
        helper.setText(R.id.item_process_deal_normal_tv_title, NullUtils.filterEmpty(item.getTitle()));
        if (item.getTitle().contains("金额")) {
            try {
                NumberFormat instance = NumberFormat.getInstance(Locale.CHINA);
                helper.setText(R.id.item_process_deal_normal_tv_player, instance.format(Double.parseDouble(String.valueOf(item.getContext()))));
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
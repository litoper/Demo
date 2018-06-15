package com.example.kadh.ui.work.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.work.bean.ProcessModuleBean;
import com.example.kadh.utils.GlideUtils;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.RxApi.RxUrl;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class WorkGridAdapter extends BaseQuickAdapter<ProcessModuleBean, BaseViewHolder> {
    public WorkGridAdapter(int layoutResId, @Nullable List<ProcessModuleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProcessModuleBean item) {
        helper.setText(R.id.work_tv_pro_type, NullUtils.filterEmpty(item.getPname()));
        ImageView iv = helper.getView(R.id.work_iv_pro_icon);
        GlideUtils.loadImageView(mContext, RxUrl.Url.BASE + item.getPico(), iv);
    }
}

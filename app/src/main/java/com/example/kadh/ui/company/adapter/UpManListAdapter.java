package com.example.kadh.ui.company.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.company.bean.UpManListBean;
import com.example.kadh.utils.GlideUtils;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.RxApi.RxUrl;
import com.example.kadh.view.CircleImageView.CircleImageView;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/6
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class UpManListAdapter extends BaseQuickAdapter<UpManListBean, BaseViewHolder> {

    public UpManListAdapter(int layoutResId, @Nullable List<UpManListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UpManListBean item) {
        helper.setText(R.id.item_fab_tv_name, NullUtils.filterNull(item.getRemarkman_name()));
        CircleImageView cvHead = helper.getView(R.id.item_fab_cv_head);
        GlideUtils.loadImageViewForHead(mContext, RxUrl.Url.BASE + item.getRemarkman_image(), cvHead);
    }
}

package com.example.kadh.ui.company.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.company.bean.CommentListBean;
import com.example.kadh.utils.GlideUtils;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.RxApi.RxApiUrl;

import java.util.List;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/6
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class CommonListAdapter extends BaseQuickAdapter<CommentListBean, BaseViewHolder> {
    private String mYourUserId;

    public CommonListAdapter(int layoutResId, @Nullable List<CommentListBean> data, String yourUserId) {
        super(layoutResId, data);
        mYourUserId = yourUserId;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentListBean item) {
        ImageView mCvHead = helper.getView(R.id.item_comment_cv_head);
        GlideUtils.loadImageViewForHead(mContext, RxApiUrl.Url.BASE + item.getRemarkman_image(), mCvHead);

        helper.setText(R.id.item_comment_tv_name, NullUtils.filterNull(item.getRemarkman_name()));
        helper.setText(R.id.item_comment_tv_time, NullUtils.filterNull(item.getUpdate_time()));
        helper.setText(R.id.item_comment_tv_value, NullUtils.filterNull(item.getRemark_comment()));
        if (item.getRemarkman_id().equals(mYourUserId)) {
            helper.setVisible(R.id.item_comment_tv_del, true);
        } else {
            helper.setVisible(R.id.item_comment_tv_del, false);
        }

    }
}

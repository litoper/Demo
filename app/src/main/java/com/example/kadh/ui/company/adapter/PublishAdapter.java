package com.example.kadh.ui.company.adapter;

import android.text.Html;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kadh.R;
import com.example.kadh.ui.company.bean.PublishListBean;
import com.example.kadh.utils.GlideUtils;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.RxApi.RxApiUrl;
import com.example.kadh.utils.ScreenUtils;
import com.example.kadh.view.CircleImageView.CircleImageView;

import java.util.List;
import java.util.Map;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class PublishAdapter extends BaseMultiItemQuickAdapter<PublishListBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public PublishAdapter(List<PublishListBean> data) {
        super(data);
        addItemType(PublishListBean.NEWS, R.layout.item_publish_news);
        addItemType(PublishListBean.NOTICE, R.layout.item_publish_notice);
        addItemType(PublishListBean.PROCESS, R.layout.item_publish_process);
        addItemType(PublishListBean.INFROM, R.layout.item_publish_meeting_infrom);
    }

    @Override
    protected void convert(BaseViewHolder helper, PublishListBean item) {
        switch (helper.getItemViewType()) {
            case PublishListBean.NEWS:
                processNews(helper, item);
                break;
            case PublishListBean.NOTICE:
                processNotice(helper, item);
                break;
            case PublishListBean.PROCESS:
                processProcess(helper, item);
                break;
            case PublishListBean.INFROM:
                processInform(helper, item);
                break;
            default:
                break;
        }
    }

//    holderForInFrom.mCvMeetingIcon = (CircleImageView) convertView.findViewById(R.id.meeting_iv_news_icon);
//    holderForInFrom.mBtnSure = (Button) convertView.findViewById(R.id.meeting_btn_sure);
//    holderForInFrom.mBtnTurnDown = (Button) convertView.findViewById(R.id.meeting_btn_turn_down);
//    holderForInFrom.mTvMeetingName = (TextView) convertView.findViewById(R.id.meeting_tv_name);
//    holderForInFrom.mTvMeetingPlace = (TextView) convertView.findViewById(R.id.meeting_tv_meeting_place);
//    holderForInFrom.mTvMeetingCreatTime = (TextView) convertView.findViewById(R.id.meeting_tv_creat_time);
//    holderForInFrom.mTvMeetingTime = (TextView) convertView.findViewById(R.id.meeting_tv_meeting_time);
//    holderForInFrom.mTvMeetingTheme = (TextView) convertView.findViewById(R.id.meeting_tv_theme);
//    holderForInFrom.mIvInFromMenu = (ImageView) convertView.findViewById(R.id.meeting_notice_iv_pop);

    private void processInform(BaseViewHolder helper, PublishListBean item) {
        Map pthing = (Map) item.getPthing();
        helper.setText(R.id.meeting_tv_name, NullUtils.filterEmpty(String.valueOf(pthing.get("psponsor"))));
        helper.setText(R.id.meeting_tv_creat_time, pthing.get("pcreate_time") + "发布");
        helper.setText(R.id.meeting_tv_meeting_time, "时间 : " + pthing.get("pstartTime"));
        helper.setText(R.id.meeting_tv_meeting_place, "地点 : " + pthing.get("pplace"));
        helper.setText(R.id.meeting_tv_theme, "主题 : " + pthing.get("ptitle"));
        GlideUtils.loadImageViewForHead(mContext, RxApiUrl.Url.BASE + item.getPsponsorImage(), (CircleImageView) helper.getView(R.id.meeting_iv_news_icon));
    }


//    holderForProcess.mCvProcessIcon = (CircleImageView) convertView.findViewById(R.id.process_iv_icon);
//    holderForProcess.mTvProcessName = (TextView) convertView.findViewById(R.id.process_tv_name);
//    holderForProcess.mTvProcessCreatTime = (TextView) convertView.findViewById(R.id.process_tv_creat_time);
//    holderForProcess.mTvProcessTime = (TextView) convertView.findViewById(R.id.process_tv_time);
//    holderForProcess.mTvProcessTitle = (TextView) convertView.findViewById(R.id.process_tv_title);
//    holderForProcess.mIvProMenu = (ImageView) convertView.findViewById(R.id.process_iv_menu);
//    holderForProcess.mRlProcess = (RelativeLayout) convertView.findViewById(R.id.process_rl);

    private void processProcess(BaseViewHolder helper, PublishListBean item) {
        helper.setText(R.id.process_tv_name, NullUtils.filterEmpty(item.getPsponsor()));
        helper.setText(R.id.process_tv_creat_time, NullUtils.filterEmpty(item.getCreate_time()) + "发布");
        helper.setText(R.id.process_tv_time, NullUtils.filterEmpty(String.valueOf(item.getPthing())));
        helper.setText(R.id.process_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        GlideUtils.loadImageViewForHead(mContext, RxApiUrl.Url.BASE + item.getPsponsorImage(), (CircleImageView) helper.getView(R.id.process_iv_icon));
    }
//
//    holderForNotice.mCvNoticeIcon = (CircleImageView) convertView.findViewById(R.id.notice_iv_icon);
//    holderForNotice.mTvNoticeName = (TextView) convertView.findViewById(R.id.notice_tv_name);
//    holderForNotice.mTvNoticeCreatTime = (TextView) convertView.findViewById(R.id.notice_tv_creat_time);
//    holderForNotice.mTvNoticeType = (TextView) convertView.findViewById(R.id.notice_tv_type);
//    holderForNotice.mTvNoticeTitle = (TextView) convertView.findViewById(R.id.notice_tv_title);
//    holderForNotice.mRlNotice = (RelativeLayout) convertView.findViewById(R.id.notice_rl);

    private void processNotice(BaseViewHolder helper, PublishListBean item) {
        helper.setText(R.id.notice_tv_creat_time, item.getCreate_time() + "发布");
        helper.setText(R.id.notice_tv_type, "类型：" + item.getPthing());
        helper.setText(R.id.notice_tv_title, "标题：" + item.getPtitle());
        helper.setText(R.id.notice_tv_name, NullUtils.filterEmpty(item.getPsponsor()));
        GlideUtils.loadImageViewForHead(mContext, RxApiUrl.Url.BASE + item.getPsponsorImage(), (CircleImageView) helper.getView(R.id.notice_iv_icon));



    }
//    holder.mCvNewsIcon = (CircleImageView) convertView.findViewById(R.id.news_iv_news_icon);
//    holder.mIvMenu = (ImageView) convertView.findViewById(R.id.news_iv_menu);
//    holder.mIvNewsIv = (ImageView) convertView.findViewById(R.id.news_iv_news_iv);
//    holder.mTvNewsName = (TextView) convertView.findViewById(R.id.news_tv_name);
//    holder.mTvNewsCreatTime = (TextView) convertView.findViewById(R.id.news_tv_createtime);
//    holder.mTvNewsTitle = (TextView) convertView.findViewById(R.id.news_tv_title);
//    holder.mTvNewsThing = (TextView) convertView.findViewById(R.id.news_tv_thing);
//    holder.mTvNum = (TextView) convertView.findViewById(R.id.news_tv_num);
//    holder.mRlNews = (RelativeLayout) convertView.findViewById(R.id.news_rl);
//    holder.mTvCommentNum = (TextView) convertView.findViewById(R.id.news_tv_comment_num);


    private void processNews(BaseViewHolder helper, PublishListBean item) {
        helper.setText(R.id.news_tv_name, NullUtils.filterEmpty(item.getPsponsor()));
        helper.setText(R.id.news_tv_createtime, NullUtils.filterEmpty(item.getCreate_time() + "发布"));
        helper.setText(R.id.news_tv_title, NullUtils.filterEmpty(item.getPtitle()));
        helper.setText(R.id.news_tv_thing, Html.fromHtml(item.getPthing().toString()));
        helper.setText(R.id.news_tv_num, NullUtils.filterEmpty(item.getSpareA()));
        helper.setText(R.id.news_tv_comment_num, NullUtils.filterEmpty(item.getSpareB()));
        GlideUtils.loadImageViewForHead(mContext, RxApiUrl.Url.BASE + item.getPsponsorImage(), (CircleImageView) helper.getView(R.id.news_iv_news_icon));
        if (!NullUtils.isNull(item.getPicture())) {

            ImageView imageView = helper.getView(R.id.news_iv_news_iv);
            int width = ScreenUtils.getScreenWidth();
            double height = width * 0.75;
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = (int) height;
            params.width = width;
            imageView.setLayoutParams(params);

            String[] split = item.getPicture().split(",");
            GlideUtils.loadImageView(mContext, RxApiUrl.Url.BASE + split[0], imageView);
        }
    }
}

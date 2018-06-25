package com.example.kadh.ui.company.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivityView;
import com.example.kadh.component.AppComponent;
import com.example.kadh.component.DaggerMainComponent;
import com.example.kadh.ui.company.adapter.CommonListAdapter;
import com.example.kadh.ui.company.adapter.UpManListAdapter;
import com.example.kadh.ui.company.bean.CommentListBean;
import com.example.kadh.ui.company.bean.PublishNewDetailBean;
import com.example.kadh.ui.company.bean.UpManListBean;
import com.example.kadh.ui.company.bean.UpNumberBean;
import com.example.kadh.ui.company.contract.NewsDetailAtyContract;
import com.example.kadh.ui.company.presenter.NewsDetailBinding;
import com.example.kadh.utils.GlideUtils;
import com.example.kadh.utils.IMEUtils;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.RxApi.RxUrl;
import com.example.kadh.utils.ScreenUtils;
import com.example.kadh.view.HoverScrollViewNew;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class NewsDetailActivity extends BaseActivityView<NewsDetailBinding> implements NewsDetailAtyContract.View, HoverScrollViewNew.OnScrollListener {


    @BindView(R.id.activity_news_details_view_line)
    View mViewLine;
    @BindView(R.id.activity_news_details_tv_title)
    TextView mTvTitle;
    @BindView(R.id.activity_news_details_tv_time)
    TextView mTvTime;
    @BindView(R.id.activity_news_details_tv_name)
    TextView mTvName;
    @BindView(R.id.activity_news_details_wv_value)
    WebView mWvValue;
    @BindView(R.id.activity_news_details_ll)
    LinearLayout mDetailsLl;
    @BindView(R.id.activity_news_details_tv_comment_num)
    TextView mTvCommentNum;
    @BindView(R.id.activity_news_details_tv_fab_num)
    TextView mTvFabNum;
    @BindView(R.id.activity_news_details_iv_share_icon)
    ImageView mIvShareIcon;
    @BindView(R.id.activity_news_details_rl_top_hover)
    RelativeLayout mRlTopHover;
    @BindView(R.id.activity_news_details_rv)
    RecyclerView mRv;
    @BindView(R.id.activity_news_details_srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.activity_news_details_scrollview)
    HoverScrollViewNew mScrollview;
    @BindView(R.id.activity_news_details_rl)
    RelativeLayout mRl;
    @BindView(R.id.activity_news_details_iv_share)
    ImageView mIvShare;
    @BindView(R.id.activity_news_details_iv_fab)
    ImageView mIvFab;
    @BindView(R.id.activity_news_details_iv_comment_icon)
    ImageView mIvCommentIcon;
    @BindView(R.id.activity_news_details_et_comment)
    EditText mEtComment;
    @BindView(R.id.activity_news_details_tv_send)
    TextView mTvSend;
    @BindView(R.id.activity_news_details_rl_bottom)
    RelativeLayout mRlBottom;
    @BindView(R.id.activity_news_details_banner)
    Banner mBanner;
    @BindView(R.id.activity_news_details_root_srl)
    SmartRefreshLayout mRootSrl;

    private WebSettings wSet;
    private String mPublishId;
    private String mNe_news__id;
    private String mYourUserId;
    private String mPtype;
    private int mPageComment = 1;
    private int mPageFab = 1;
    private List<CommentListBean> mCommentListBeans = new ArrayList<>();
    private List<UpManListBean> mUpManListBeans = new ArrayList<>();
    private CommonListAdapter mCommonListAdapter;
    private UpManListAdapter mUpManListAdapter;
    private int mViewGroupTop;
    private PublishNewDetailBean mDetailBean;
    private ViewGroup.LayoutParams mLinearParams;
    private int mEtCommentDefaultHeight;

    @Override
    public void configViews() {

        mEtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int mCount = mEtComment.getLineCount();
                if (mCount > 1) {
                    mLinearParams.height = 180;
                    mEtComment.setLayoutParams(mLinearParams);
                } else if (mCount == 1) {
                    mLinearParams.height = mEtCommentDefaultHeight;
                    mEtComment.setLayoutParams(mLinearParams);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEtComment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mIvFab.setVisibility(View.GONE);
                    mIvShare.setVisibility(View.INVISIBLE);
                    mTvSend.setVisibility(View.VISIBLE);
                } else {
                    mIvFab.setVisibility(View.VISIBLE);
                    mIvShare.setVisibility(View.VISIBLE);
                    mTvSend.setVisibility(View.GONE);
                }
            }
        });

        mRootSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getNewsDetailByPublishId(mPublishId);
            }
        });
    }

    @Override
    public void initDatas() {
        initWebView();
        mCommonListAdapter = new CommonListAdapter(R.layout.item_publish_comment_list, mCommentListBeans, mYourUserId);
        mUpManListAdapter = new UpManListAdapter(R.layout.item_publish_fab_list, mUpManListBeans);
        mRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mCommonListAdapter);
        mPublishId = getIntent().getStringExtra("publishId");
        mLinearParams = mEtComment.getLayoutParams();
        mEtCommentDefaultHeight = mEtComment.getLayoutParams().height;
        mRootSrl.autoRefresh();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        wSet = mWvValue.getSettings();
        wSet.setJavaScriptEnabled(true);
        wSet.setNeedInitialFocus(false);
        wSet.setSupportZoom(true);
        wSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wSet.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        wSet.setLoadsImagesAutomatically(true);
        wSet.setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("新闻");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mian_news_detail;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showNewsDetailByPublishId(PublishNewDetailBean detailBean) {
        mDetailBean = detailBean;
        mYourUserId = detailBean.getYourUserId();
        mNe_news__id = detailBean.getNe_news__id();
        mPtype = detailBean.getPtype();
        setNewsDetail(detailBean);
        setNewsOther(detailBean);
        mPresenter.getCommentList(mPageComment, mNe_news__id, mPtype);
        if (mRootSrl.isEnableRefresh()) {
            mRootSrl.setEnableRefresh(false);
            mRootSrl.finishRefresh(300);
        }
    }

    @Override
    public void showCommentList(List<CommentListBean> data, String total) {
        if (mPageComment == 1) {
            mCommentListBeans.clear();
        }

        mCommentListBeans.addAll(data);

        if (!(mCommentListBeans.size() > 0) || mCommentListBeans.size() >= Integer.parseInt(total)) {
            mSrl.finishLoadMore(true);
            mSrl.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSrl.setEnableLoadMore(false);
                    mSrl.setEnableAutoLoadMore(false);
                    mSrl.setEnableOverScrollBounce(false);
                }
            }, 1000);
        } else {
            mSrl.finishLoadMore(false);
            mSrl.setEnableLoadMore(true);
            mSrl.setEnableAutoLoadMore(true);
            mSrl.setEnableOverScrollBounce(true);
        }
        mSrl.finishLoadMore();
        mRv.setAdapter(mCommonListAdapter);
        mCommonListAdapter.setNewData(mCommentListBeans);


        showUnderline(mTvCommentNum);
        hideUnderline(mTvFabNum);
        mPageFab = 1;
    }

    @Override
    public void showUpManList(List<UpManListBean> data, String total) {
        if (mPageFab == 1) {
            mUpManListBeans.clear();
        }
        mUpManListBeans.addAll(data);

        if (!(mUpManListBeans.size() > 0) || mUpManListBeans.size() >= Integer.parseInt(total)) {
            mSrl.finishLoadMore(true);
            mSrl.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSrl.setEnableLoadMore(false);
                    mSrl.setEnableAutoLoadMore(false);
                    mSrl.setEnableOverScrollBounce(false);
                }
            }, 1000);
        } else {
            mSrl.finishLoadMore(false);
            mSrl.setEnableLoadMore(true);
            mSrl.setEnableAutoLoadMore(true);
            mSrl.setEnableOverScrollBounce(true);
        }
        mSrl.finishLoadMore();
        mUpManListAdapter.setNewData(mUpManListBeans);
        mRv.setAdapter(mUpManListAdapter);

        showUnderline(mTvFabNum);
        hideUnderline(mTvCommentNum);
        mPageComment = 1;
    }

    @Override
    public void upNumberSuccess(UpNumberBean upNumberBean) {
        mTvFabNum.setText("已赞(" + upNumberBean.getNpraise_num() + ")");
        mDetailBean.setUped(NullUtils.filterNull(upNumberBean.getUped(), "0"));
        switch (upNumberBean.getUped()) {
            case "0":
                mIvFab.setImageResource(R.mipmap.company_icon_fab_unselected);
                break;
            case "1":
                mIvFab.setImageResource(R.mipmap.company_icon_fab_selceted);
                break;
            default:
                break;
        }
        mPresenter.getUpManList(mPageFab = 1, mNe_news__id, mPtype);
    }

    @Override
    public void addCommentSuccess(PublishNewDetailBean detailBean) {
        Toast.makeText(mContext, "发表评论成功", Toast.LENGTH_SHORT).show();
        mTvCommentNum.setText("评论 ( " + NullUtils.filterNull(detailBean.getNremark_num(), "0") + " )");
        mEtComment.setText("");
        mEtComment.clearFocus();
        IMEUtils.hideSoftInput(mEtComment);
        mPresenter.getCommentList(mPageComment = 1, mNe_news__id, mPtype);
    }

    private void showUnderline(TextView tv) {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.shape_rectangle_bule);
        drawable.setBounds(0, 0, tv.getWidth(), drawable.getIntrinsicHeight());
        tv.setCompoundDrawables(null, null, null, drawable);
        tv.setCompoundDrawablePadding((int) ScreenUtils.dpToPx(5));
        tv.setTextColor(getResources().getColor(R.color.black_text));
    }

    private void hideUnderline(TextView tv) {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.shape_rectangle_tran);
        drawable.setBounds(0, 0, tv.getWidth(), drawable.getIntrinsicHeight());
        tv.setCompoundDrawables(null, null, null, drawable);
        tv.setCompoundDrawablePadding((int) ScreenUtils.dpToPx(5));
        tv.setTextColor(getResources().getColor(R.color.gray_text));
    }

    private void setNewsOther(PublishNewDetailBean detailBean) {
        mTvTitle.setText(NullUtils.filterNull(detailBean.getNtitle()));
        mTvTime.setText(NullUtils.filterNull(detailBean.getCreate_time().split(" ")[1]));
        mTvName.setText(NullUtils.filterNull(detailBean.getPsponsor()));
        mTvCommentNum.setText("评论(" + NullUtils.filterNull(detailBean.getNremark_num(), "0") + ")");
        mTvFabNum.setText("已赞(" + NullUtils.filterNull(detailBean.getNpraise_num(), "0") + ")");
        switch (detailBean.getUped()) {
            case "0":
                mIvFab.setImageResource(R.mipmap.company_icon_fab_unselected);
                break;
            case "1":
                mIvFab.setImageResource(R.mipmap.company_icon_fab_selceted);
                break;
            default:
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setNewsDetail(PublishNewDetailBean newsDetail) {
        Document doc = Jsoup.parse(newsDetail.getNnews_content());
        Elements elements = doc.getElementsByTag("img");
        Elements tag = doc.getElementsByTag("a");
        if (elements.size() != 0) {
            for (Element img : elements) {
                img.attr("width", "100%");
                img.attr("height", "auto");
            }
        }
        if (tag.size() != 0) {
            for (Element url : tag) {
                url.attr("style", " word-break: break-all");
            }
        }

        String url = doc.toString();
        mWvValue.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
        mWvValue.setVisibility(View.VISIBLE);
        mWvValue.canGoForward();//设置webview可以前进
        mWvValue.canGoBack();//设置webview可以后退
        mWvValue.setBackgroundColor(getResources().getColor(R.color.white));
        mWvValue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GlideUtils.loadImageView(context, RxUrl.Url.BASE + String.valueOf(path), imageView);
            }
        }).setImages(Arrays.asList(newsDetail.getNpicture().split(","))).start();
    }

    @OnClick({R.id.activity_news_details_tv_comment_num, R.id.activity_news_details_tv_fab_num, R.id.activity_news_details_tv_send, R.id.activity_news_details_iv_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_news_details_tv_fab_num:
                mPresenter.getUpManList(mPageFab, mNe_news__id, mPtype);
                break;
            case R.id.activity_news_details_tv_comment_num:
                mPresenter.getCommentList(mPageComment, mNe_news__id, mPtype);
                break;
            case R.id.activity_news_details_tv_send:
                addComment();
                break;
            case R.id.activity_news_details_iv_fab:
                mPresenter.upNumber(mPtype, mPublishId, mDetailBean);
                break;
            default:
                break;
        }
    }

    private void addComment() {
        String trim = mEtComment.getText().toString().trim();
        if (NullUtils.isEmpty(trim)) {
            Toast.makeText(mContext, "请输入评论", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.addComment(mPtype, mNe_news__id, trim);
    }


    @Override
    public void onScroll(int scrollY) {
        //判断滚动距离是否在于评论高度
        mViewGroupTop = mDetailsLl.getHeight();

        if (scrollY >= mViewGroupTop) {
            if (mTvCommentNum.getParent() != mRl) { //判断其现有父类
                mRl.setVisibility(View.VISIBLE);
                mRlTopHover.removeView(mTvCommentNum);  //从现有父类移除
                mRlTopHover.removeView(mTvFabNum);  //从现有父类移除
                mRlTopHover.removeView(mIvShare);  //从现有父类移除

                mRl.addView(mTvCommentNum);  //添加到目标父类
                mRl.addView(mTvFabNum);  //添加到目标父类
                mRl.addView(mIvShare);  //添加到目标父类
            }
        } else {
            if (mTvCommentNum.getParent() != mRlTopHover) {
                mRl.setVisibility(View.INVISIBLE);
                mRl.removeView(mTvCommentNum);
                mRl.removeView(mTvFabNum);
                mRl.removeView(mIvShare);

                mRlTopHover.addView(mTvCommentNum);
                mRlTopHover.addView(mTvFabNum);
                mRlTopHover.addView(mIvShare);
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }
}

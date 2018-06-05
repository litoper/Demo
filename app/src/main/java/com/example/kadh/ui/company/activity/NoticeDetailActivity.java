package com.example.kadh.ui.company.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.ui.company.bean.PublishNoticeDetailBean;
import com.example.kadh.utils.NullUtils;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxApi.RxManager;
import com.example.kadh.utils.RxJava.RxSubscriber.SubNextImpl;
import com.example.kadh.utils.RxJava.RxSubscriber.SubProtect;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import butterknife.BindView;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/5
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.activity_notice_details_tv_title)
    TextView mTvTitle;
    @BindView(R.id.activity_notice_details_tv_time)
    TextView mTvTime;
    @BindView(R.id.activity_notice_details_tv_name)
    TextView mTvName;
    @BindView(R.id.activity_notice_details_wv_value)
    WebView mWvValue;
    private String mPublishId;
    private WebSettings wSet;
    private List<PublishNoticeDetailBean.FileListModel> mFileList;

    @Override
    public void configViews() {


    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initDatas() {
        wSet = mWvValue.getSettings();
        wSet.setJavaScriptEnabled(true);
        wSet.setNeedInitialFocus(false);
        wSet.setSupportZoom(true);
        wSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wSet.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        wSet.setLoadsImagesAutomatically(true);
//        wSet.setCacheMode(WebSettings.LOAD_DEFAULT | WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wSet.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWvValue.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });


        mPublishId = getIntent().getStringExtra("publishId");

        SubProtect<BaseResponse<List<PublishNoticeDetailBean>>> subProtect = new SubProtect<>(new SubNextImpl<BaseResponse<List<PublishNoticeDetailBean>>>() {
            @Override
            public void onSubSuccess(BaseResponse<List<PublishNoticeDetailBean>> response) {
                PublishNoticeDetailBean detailBean = response.data.get(0);
                mTvTitle.setText(detailBean.getNtitle());
                mTvTime.setText(detailBean.getCreate_time().substring(0, 10));
                mTvName.setText(detailBean.getPsponsor());

                mFileList = detailBean.getFileList();
                if (!NullUtils.isNull(mFileList)) {
                    // TODO: 2018/6/5 附件下载
                }

                setWebView(detailBean);

            }
        });
        RxManager.getInstant().getRxApi().getNoticeDetailByPublishId(subProtect, mPublishId);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setWebView(PublishNoticeDetailBean detailBean) {
        if (detailBean.getNnews_content() != null) {
            Document doc = Jsoup.parse(detailBean.getNnews_content());
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
        }

        mWvValue.setVisibility(View.VISIBLE);
        mWvValue.setBackgroundColor(Color.WHITE);
        mWvValue.canGoForward();
        mWvValue.canGoBack();
        mWvValue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("企业发文");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mian_notice_detail;
    }


    @Override
    protected void onStop() {
        super.onStop();
        wSet.setJavaScriptEnabled(false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onResume() {
        super.onResume();
        wSet.setJavaScriptEnabled(true);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}

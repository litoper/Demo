package com.example.kadh.ui.person.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.kadh.R;
import com.example.kadh.base.BaseActivity;
import com.example.kadh.component.AppComponent;
import com.example.kadh.utils.RxJava.RxApi.RxUrl;
import com.example.kadh.view.LoadingLayout;

import butterknife.BindView;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/7/4
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class HelpActivity extends BaseActivity {
    @BindView(R.id.activity_help_webview)
    WebView mWebView;
    @BindView(R.id.activity_help_loading)
    LoadingLayout mLoading;
    private WebSettings mSetting;

    @Override
    public void configViews() {

    }

    @Override
    public void initDatas() {
        mSetting = mWebView.getSettings();
        mSetting.setJavaScriptEnabled(true);
        mWebView.canGoForward();//设置webview可以前进
        mWebView.canGoBack();//设置webview可以后退

        //支持获取手势焦点
        mWebView.requestFocusFromTouch();

        //支持js
        mSetting.setJavaScriptEnabled(true);
        //支持插件
        mSetting.setPluginState(WebSettings.PluginState.ON);
        //设置适应屏幕
        mSetting.setUseWideViewPort(true);
        mSetting.setLoadWithOverviewMode(true);
        //支持缩放
        mSetting.setSupportZoom(false);
        //隐藏原生的缩放控件
        mSetting.setDisplayZoomControls(false);
        //支持内容重新布局
        mSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mSetting.supportMultipleWindows();
        mSetting.setSupportMultipleWindows(true);
        //设置缓存模式
        mSetting.setDomStorageEnabled(true);
        mSetting.setDatabaseEnabled(true);
        mSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        mSetting.setAppCacheEnabled(true);
        mSetting.setAppCachePath(mWebView.getContext().getCacheDir().getAbsolutePath());
        //设置可访问文件
        mSetting.setAllowContentAccess(true);
        //当webview调用requestFocus时为webview设置节点
        mSetting.setNeedInitialFocus(true);
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            mSetting.setLoadsImagesAutomatically(true);
        } else {
            mSetting.setLoadsImagesAutomatically(false);
        }
        mSetting.setNeedInitialFocus(true);
        //设置编码格式
        mSetting.setDefaultTextEncodingName("UTF-8");

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });


        mWebView.setWebViewClient(new WebViewClient() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return super.shouldInterceptRequest(view, url);
            }

            //页面开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }


            //页面完成加载
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mLoading.showContent();
            }

            //是否在WebView内加载新页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);

            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            //网络错误时回调的方法
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }


            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }
        });

        mWebView.loadUrl(RxUrl.Url.BASE + "personal/help.html");
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("帮助与反馈");
        mCommonToolbar.setNavigationIcon(R.drawable.common_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

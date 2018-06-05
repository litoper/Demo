package com.example.kadh.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.kadh.R;
import com.example.kadh.utils.RxJava.RxApi.RxManager;

import java.util.List;

import okhttp3.Cookie;

/**
 * Glide图片缓存
 * author: ZhangXiaoWei
 * email : cherno@126.com
 * blog  : http://www.jianshu.com/users/0f532bf88454/latest_articles
 * time  : 2017/6/27
 * desc  :
 */

public class GlideUtils {
    //默认加载
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.errorOf(R.mipmap.work_pic_loading2x)).into(mImageView);
    }

    //图片请求带cookie
    public static void loadImageViewForCookie(final Context mContext, String path, ImageView mImageView) {
        List<Cookie> cookies = RxManager.getInstant().getCookie().loadAll();
        Glide.with(mContext).load(new GlideUrl(path, new LazyHeaders.Builder().addHeader("Cookie",
                cookies.get(0).value())
                .build())).apply(RequestOptions.errorOf(R.mipmap.work_pic_loading2x).placeholder(R.mipmap.work_pic_loading2x)
//                DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
//                DiskCacheStrategy.NONE 不使用磁盘缓存
//                DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
//                DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
//                DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
                .dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(mImageView);
    }

    //头像
    public static void loadImageViewForHead(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.centerCropTransform().placeholder(R.mipmap.personal_head).error(R.mipmap.personal_head)).into(mImageView);
    }

    //加载指定大小
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.overrideOf(width, height)).into(mImageView);
    }

    //设置加载中以及加载失败图片
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.placeholderOf(lodingImage).error(errorImageView)).into(mImageView);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.overrideOf(width, height).placeholder(lodingImage).error(errorImageView)).into(mImageView);
    }

    //设置跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.skipMemoryCacheOf(true)).into(mImageView);
    }

    //设置下载优先级
    public static void loadImageViewPriority(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.priorityOf(Priority.NORMAL)).into(mImageView);
    }

    /**
     * 策略解说：
     * <p>
     * all:缓存源资源和转换后的资源
     * <p>
     * none:不作任何磁盘缓存
     * <p>
     * source:缓存源资源
     * <p>
     * result：缓存转换后的资源
     */

    //设置缓存策略
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).into(mImageView);
    }

    /**
     * api也提供了几个常用的动画：比如crossFade()
     */

    //设置加载动画
    public static void loadImageViewAnim(Context mContext, String path, TransitionOptions anim, ImageView mImageView) {
        Glide.with(mContext).load(path).transition(anim).into(mImageView);
    }

    /**
     * 会先加载缩略图
     */

    //设置缩略图支持
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }

    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //设置动态转换
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).apply(RequestOptions.centerCropTransform()).into(mImageView);
    }

    //设置动态GIF加载方式
    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).asGif().load(path).into(mImageView);
    }

    //设置静态GIF加载方式
    public static void loadImageViewStaticGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).asBitmap().load(path).into(mImageView);
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

    //设置监听请求接口
    public static void loadImageViewListener(Context mContext, String path, ImageView mImageView, RequestListener<Drawable> requstlistener) {
        Glide.with(mContext).load(path).listener(requstlistener).into(mImageView);
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
    public static void loadImageViewContent(Context mContext, String path, SimpleTarget<Drawable> simpleTarget) {
        Glide.with(mContext).load(path).apply(RequestOptions.centerCropTransform()).into(simpleTarget);
    }


    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }

}

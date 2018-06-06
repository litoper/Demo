package com.example.kadh.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.kadh.R;
import com.example.kadh.utils.ScreenUtils;

/**
 * author: ZhangXiaoWei
 * email : cherno@126.com
 * blog  : http://www.jianshu.com/users/0f532bf88454/latest_articles
 * time  : 2017/7/26
 * desc  :
 */

/**
 * ScrollView并没有实现滚动监听，所以我们必须自行实现对ScrollView的监听，
 * 我们很自然的想到在onTouchEvent()方法中实现对滚动Y轴进行监听
 * ScrollView的滚动Y值进行监听
 */
public class HoverScrollViewNew extends NestedScrollView {
    private static final String TAG = "HoverScrollViewNew";
    //回调接口的对象
    private OnScrollListener onScrollListener;

    private RelativeLayout rl;
    private RecyclerView mRl;

    private boolean RvNestedScrollingEnabled = true;
    //判断是否按在list上面
    private boolean isDownList = false;
    //因为现在listview上拖动,到顶了,然后到了滚动条,因为拖了一段距离,所以会闪烁跳动一下,所以这里保存了一些值
    private float down_y, diff_y;
    private boolean diff_flag = false;

    //评论和已赞是否已经附贴在上面了
    private boolean mCommentAndFtabIsTop = false;

    public HoverScrollViewNew(Context context) {
        super(context);
    }

    public HoverScrollViewNew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HoverScrollViewNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        rl = (RelativeLayout) findViewById(R.id.activity_news_details_rl_top_hover);
        mRl = (RecyclerView) findViewById(R.id.activity_news_details_rv);
    }

    /**
     * 在滑动的时候调用我们自己写的回调方法，来获取滑动距离
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) {
            onScrollListener.onScroll(t);
        }
    }

    /**
     * 滑动回调监听的接口
     */
    public interface OnScrollListener {
        /**
         * 回调方法，返回MyScrollView在Y轴方向的滑动距离
         */
        public void onScroll(int scrollY);
    }

    public OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int[] l = new int[2];
//        rl.getLocationInWindow(l);
//        l[1] -= ScreenUtils.getTopViewHeight(getContext());
//        if (l[1] == 0) {
//            return false;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int[] l = new int[2];
//        rl.getLocationInWindow(l);
//        l[1] -= ScreenUtils.getTopViewHeight(getContext());
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                //如果手势按在了listview上面
//                if (ev.getY() >= l[1] + rl.getHeight()) {
//                    isDownList = true;
//                }
//                //如果手势按在了listview上面 而且 评论和已赞的头部是依附在头部的
//                if (isDownList && l[1] <= 0) {
//                    down_y = ev.getY();
//                    diff_flag = true;
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (isDownList && l[1] > 0) {
//                    if (diff_flag) {
//                        //拖动的偏移还原
//                        ev.offsetLocation(0, -diff_y);
//                    }
//                    onTouchEvent(ev);
//                    return true;
//                }
//                if (diff_flag) {
//                    diff_y = ev.getY() - down_y;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                isDownList = false;
//                diff_flag = false;
//                down_y = 0;
//                diff_y = 0;
//                break;
//            default:
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        int[] l = new int[2];
        rl.getLocationInWindow(l);
        l[1] -= ScreenUtils.dpToPx(65);
        if (l[1] > 0) {
            scrollBy(0, dy);
            consumed[1] = dy;
        } else {
            super.onNestedPreScroll(target, dx, dy, consumed);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int[] l = new int[2];
        rl.getLocationInWindow(l);
        l[1] -= ScreenUtils.dpToPx(65);
        //因为滚动条的惯性滑动会被RecyclerView所接收到,所以在他到达顶部之前,我们禁止RecyclerView接受滚动条的任何滚动事件
        if (RvNestedScrollingEnabled != (l[1] == 0)) {
            RvNestedScrollingEnabled = (l[1] == 0);
            mRl.setNestedScrollingEnabled(RvNestedScrollingEnabled);
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isCommentAndFtabIsTop() {
        return mCommentAndFtabIsTop;
    }

    public void setCommentAndFtabIsTop(boolean CommentAndFtabIsTop) {
        this.mCommentAndFtabIsTop = CommentAndFtabIsTop;
    }
}

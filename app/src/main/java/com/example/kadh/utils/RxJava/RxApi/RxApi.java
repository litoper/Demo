package com.example.kadh.utils.RxJava.RxApi;


import com.example.kadh.ui.company.bean.CommentListBean;
import com.example.kadh.ui.company.bean.PublishListBean;
import com.example.kadh.ui.company.bean.PublishNewDetailBean;
import com.example.kadh.ui.company.bean.PublishNoticeDetailBean;
import com.example.kadh.ui.company.bean.UpManListBean;
import com.example.kadh.ui.company.bean.UpNumberBean;
import com.example.kadh.ui.login.bean.LoginBean;
import com.example.kadh.ui.main.bean.IsHasUnReadBean;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.main.bean.WeatherBean;
import com.example.kadh.ui.person.bean.QueryProTotalInfoBean;
import com.example.kadh.ui.work.bean.ProcessModuleBean;
import com.example.kadh.ui.work.bean.ProcessStatusBean;
import com.example.kadh.utils.RxJava.BaseResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */


public class RxApi {
    private RxApiService mRxApiService;
    private String version;

    RxApi(RxApiService rxApiService, String version) {
        mRxApiService = rxApiService;
        this.version = version;
    }

    /**
     * login登陆
     *
     * @param subscriber
     * @param username
     * @param password
     */
    public void login(FlowableSubscriber<BaseResponse<List<LoginBean>>> subscriber, String username, String password) {
        Flowable flowable = mRxApiService.login(username, password, version);
        toSubscribe(flowable, subscriber);
    }

    /**
     * getUseInfo获取个人信息
     *
     * @param subscriber
     * @param userid
     */
    public Flowable getUseInfo(FlowableSubscriber<BaseResponse<List<UserInfoBean>>> subscriber, String userid) {
        Flowable flowable = mRxApiService.getUseInfo(userid, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * isHasUnRead  app端主页消息列表中是否存在未读消息
     *
     * @param subscriber
     */
    public Flowable isHasUnRead(FlowableSubscriber<BaseResponse<IsHasUnReadBean>> subscriber) {
        Flowable flowable = mRxApiService.isHasUnRead(version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getWeather 通过地点获取天气
     *
     * @param subscriber
     * @param place
     */
    public Flowable getWeather(FlowableSubscriber<BaseResponse<List<WeatherBean>>> subscriber, String place) {
        Flowable flowable = mRxApiService.getWeather(place, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getPublishList 获取发文列表
     *
     * @param subscriber
     * @param page
     * @param ptype
     */
    public Flowable getPublishList(FlowableSubscriber<BaseResponse<List<PublishListBean>>> subscriber, String page, String ptype) {
        Flowable flowable = mRxApiService.getPublishList(page, ptype, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getNoticeDetailByPublishId 根据发文表ID获取公告详情
     *
     * @param subscriber
     * @param proPublishId
     */
    public Flowable getNoticeDetailByPublishId(FlowableSubscriber<BaseResponse<List<PublishNoticeDetailBean>>> subscriber, String proPublishId) {
        Flowable flowable = mRxApiService.getNoticeDetailByPublishId(proPublishId, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getProcessModuletList 获取流程模块列表
     */
    public Flowable getProcessModuletList(FlowableSubscriber<BaseResponse<List<ProcessModuleBean>>> subscriber) {
        Flowable flowable = mRxApiService.getProcessModuleList(version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getProcessStatus 获取工作当前流程状态
     *
     * @param subscriber
     */
    public Flowable getProcessStatus(FlowableSubscriber<BaseResponse<List<ProcessStatusBean>>> subscriber) {
        Flowable flowable = mRxApiService.getProcessStatus(version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * queryProcessBaseTotalInfo 时长统计
     *
     * @param subscriber
     */
    public Flowable queryProcessBaseTotalInfo(FlowableSubscriber<BaseResponse<QueryProTotalInfoBean>> subscriber) {
        Flowable flowable = mRxApiService.queryProcessBaseTotalInfo(version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getNoticeDetailByPublishId 根据首页新闻ID获取新闻详情
     *
     * @param subscriber
     * @param proPublishId
     */
    public Flowable getNewsDetailByPublishId(FlowableSubscriber<BaseResponse<List<PublishNewDetailBean>>> subscriber, String proPublishId) {
        Flowable flowable = mRxApiService.getNewsDetailByPublishId(proPublishId, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getCommentList 获取评论列表
     *
     * @param subscriber
     * @param page
     * @param id
     * @param ptype
     */
    public Flowable getCommentList(FlowableSubscriber<BaseResponse<List<CommentListBean>>> subscriber, String page, String id, String ptype) {
        Flowable flowable = mRxApiService.getCommentList(page, id, ptype, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getUpManList 获取点赞列表
     *
     * @param subscriber
     * @param page
     * @param id
     * @param ptype
     */
    public Flowable getUpManList(FlowableSubscriber<BaseResponse<List<UpManListBean>>> subscriber, String page, String id, String ptype) {
        Flowable flowable = mRxApiService.getUpManList(page, id, ptype, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * addComment 添加评论
     *
     * @param subscriber
     * @param ptype
     * @param id
     * @param remark_comment
     */
    public Flowable addComment(FlowableSubscriber<BaseResponse<List<PublishNewDetailBean>>> subscriber, String ptype, String id, String remark_comment) {
        Flowable flowable = mRxApiService.addComment(ptype, id, remark_comment, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * delComment 删除评论
     *
     * @param subscriber
     * @param remarkId
     */
    public Flowable delComment(FlowableSubscriber<BaseResponse<String>> subscriber, String ptype, String remarkId) {
        Flowable flowable = mRxApiService.delComment(ptype, remarkId, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * upNumber 点赞功能
     *
     * @param subscriber
     * @param proPublishId
     */
    public Flowable upNumber(FlowableSubscriber<BaseResponse<List<UpNumberBean>>> subscriber, String ptype, String proPublishId, String uped) {
        Flowable flowable = mRxApiService.upNumber(ptype, proPublishId, uped, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    // subscribeOn(): 指定 subscribe() 发生在 IO 线程
    // observeOn(): 指定 Subscriber 的回调发生在主线程
    private void toSubscribe(Flowable flowable, FlowableSubscriber subscriber) {
        if (subscriber != null) {
            flowable
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }

    public void toConcatSub(FlowableSubscriber subscriber, @NonNull Flowable... flowables) {
        Flowable flowable = Flowable.concatArray(flowables);
        toSubscribe(flowable, subscriber);
    }

    public void toMergeSub(FlowableSubscriber subscriber, @NonNull Flowable... flowables) {
        Flowable flowable = Flowable.mergeArray(flowables);
        toSubscribe(flowable, subscriber);
    }

}

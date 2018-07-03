package com.example.kadh.utils.RxJava.RxApi;


import com.example.kadh.bean.isValiDateProcessPermitBean;
import com.example.kadh.ui.company.bean.CommentListBean;
import com.example.kadh.ui.company.bean.MessageBean;
import com.example.kadh.ui.company.bean.PublishListBean;
import com.example.kadh.ui.company.bean.PublishNewDetailBean;
import com.example.kadh.ui.company.bean.PublishNoticeDetailBean;
import com.example.kadh.ui.company.bean.UpManListBean;
import com.example.kadh.ui.company.bean.UpNumberBean;
import com.example.kadh.ui.contacts.bean.ContactRecentBean;
import com.example.kadh.ui.login.bean.LoginBean;
import com.example.kadh.ui.main.bean.IsHasUnReadBean;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.main.bean.WeatherBean;
import com.example.kadh.ui.person.bean.QueryProTotalInfoBean;
import com.example.kadh.ui.person.bean.RoleManageBean;
import com.example.kadh.ui.person.bean.UpFieldBean;
import com.example.kadh.ui.work.bean.ProcessAlReadyBean;
import com.example.kadh.ui.work.bean.ProcessContentBean;
import com.example.kadh.ui.work.bean.ProcessListBean;
import com.example.kadh.ui.work.bean.ProcessModuleBean;
import com.example.kadh.ui.work.bean.ProcessScheuleBean;
import com.example.kadh.ui.work.bean.ProcessStatusBean;
import com.example.kadh.ui.work.bean.ProcessUserDetailBean;
import com.example.kadh.utils.RxJava.BaseResponse;
import com.example.kadh.utils.RxJava.RxSubscriber.SubDownload;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */


public class RxApi {
    private RxService mRxService;
    private String version;

    RxApi(RxService rxService, String version) {
        mRxService = rxService;
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
        Flowable flowable = mRxService.login(username, password, version);
        toSubscribe(flowable, subscriber);
    }

    /**
     * getUseInfo获取个人信息
     *
     * @param subscriber
     * @param userid
     */
    public Flowable getUseInfo(FlowableSubscriber<BaseResponse<List<UserInfoBean>>> subscriber, String userid) {
        Flowable flowable = mRxService.getUseInfo(userid, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getRoleManageSingle 获取个人默认角色
     *
     * @param subscriber
     */
    public Flowable getRoleManageSingle(FlowableSubscriber<BaseResponse<List<RoleManageBean>>> subscriber, @NonNull String userid) {
        Flowable flowable = mRxService.getRoleManageSingle(userid, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * isHasUnRead  app端主页消息列表中是否存在未读消息
     *
     * @param subscriber
     */
    public Flowable isHasUnRead(FlowableSubscriber<BaseResponse<IsHasUnReadBean>> subscriber) {
        Flowable flowable = mRxService.isHasUnRead(version);
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
        Flowable flowable = mRxService.getWeather(place, version);
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
        Flowable flowable = mRxService.getPublishList(page, ptype, version);
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
        Flowable flowable = mRxService.getNoticeDetailByPublishId(proPublishId, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getProcessModuletList 获取流程模块列表
     */
    public Flowable getProcessModuletList(FlowableSubscriber<BaseResponse<List<ProcessModuleBean>>> subscriber) {
        Flowable flowable = mRxService.getProcessModuleList(version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getProcessStatus 获取工作当前流程状态
     *
     * @param subscriber
     */
    public Flowable getProcessStatus(FlowableSubscriber<BaseResponse<List<ProcessStatusBean>>> subscriber) {
        Flowable flowable = mRxService.getProcessStatus(version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * queryProcessBaseTotalInfo 时长统计
     *
     * @param subscriber
     */
    public Flowable queryProcessBaseTotalInfo(FlowableSubscriber<BaseResponse<QueryProTotalInfoBean>> subscriber) {
        Flowable flowable = mRxService.queryProcessBaseTotalInfo(version);
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
        Flowable flowable = mRxService.getNewsDetailByPublishId(proPublishId, version);
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
        Flowable flowable = mRxService.getCommentList(page, id, ptype, version);
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
        Flowable flowable = mRxService.getUpManList(page, id, ptype, version);
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
        Flowable flowable = mRxService.addComment(ptype, id, remark_comment, version);
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
        Flowable flowable = mRxService.delComment(ptype, remarkId, version);
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
        Flowable flowable = mRxService.upNumber(ptype, proPublishId, uped, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    /**
     * 判断当前用户是否已经审核过了待办列表中的流程
     *
     * @param subscriber
     * @param allowId
     */
    public Flowable isVaLiDateProcessPermit(FlowableSubscriber<BaseResponse<isValiDateProcessPermitBean>> subscriber, String allowId) {
        Flowable flowable = mRxService.isVaLiDateProcessPermit(allowId, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * mqRegister 消息队列注册
     *
     * @param subscriber
     * @param devicetoken
     * @param fOS
     */
    public Flowable mqRegister(FlowableSubscriber<BaseResponse<String>> subscriber, String devicetoken, String fOS) {
        Flowable flowable = mRxService.mqRegister(devicetoken, fOS, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * mqRegister 消息队列注销
     *
     * @param subscriber
     * @param devicetoken
     * @param fOS
     */
    public Flowable mqLogOut(FlowableSubscriber<BaseResponse<String>> subscriber, String devicetoken, String fOS) {
        Flowable flowable = mRxService.mqLogOut(devicetoken, fOS, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    /**
     * mqPushMsgList 获取消息队列列表
     *
     * @param subscriber
     * @param pagesize
     * @param currentPage
     * @param state
     */
    public Flowable mqPushMsgList(FlowableSubscriber<BaseResponse<List<MessageBean>>> subscriber, String pagesize, String currentPage, String state) {
        Flowable flowable = mRxService.mqPushMsgList(pagesize, currentPage, state, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * downloadFil 文件下载
     *
     * @param url         下载路径
     * @param savePath    保存路径
     * @param fileName    文件名称
     * @param subDownload 进度监听
     */

    public void downloadFile(String url, String savePath, String fileName, SubDownload<File> subDownload) {
        Flowable<ResponseBody> flowable = mRxService.download(url);
        toSubScribe(savePath, fileName, subDownload, flowable);
    }


    /**
     * upField 头像上传
     *
     * @param subscriber
     * @param file
     * @param filename
     * @return
     */
    public Flowable upField(FlowableSubscriber<BaseResponse<List<UpFieldBean>>> subscriber, MultipartBody.Part file, String filename) {
        Flowable flowable = mRxService.upField(file, version, filename);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * 图片及附件上传
     *
     * @param subscriber
     * @param file
     * @param filename
     * @return
     */
    public Flowable upLoadField(FlowableSubscriber<BaseResponse<List<UpFieldBean>>> subscriber, MultipartBody.Part file, String filename) {
        Flowable flowable = mRxService.upLoadField(file, version, filename);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    public Flowable upLoadField(MultipartBody.Part file, String filename) {
        Flowable flowable = mRxService.upLoadField(file, version, filename);
        return flowable;
    }

//    /**
//     * upLoadField多文件上传
//     */
//    public Flowable upLoadField(FlowableSubscriber<BaseResponse<List<UpFieldBean>>> subscriber, List<MultipartBody.Part> files, List<String> filenames) {
//        Flowable[] os = new Flowable[files.size()];
//        for (int i = 0; i < files.size(); i++) {
//            Flowable observable = mRxService.upLoadField(files.get(i), version, filenames.get(i));
//            os[i] = observable;
//        }
//        toSubscribe(Flowable.merge(os), subscriber);
//    }

    /**
     * 上传修改的个人信息
     *
     * @param subscriber
     * @param uimage
     * @param id
     * @param uemail
     * @return
     */
    public Flowable postUserInfo(FlowableSubscriber<BaseResponse<String>> subscriber, String uimage, String id, String uemail) {
        Flowable flowable = mRxService.postUserInfo(uimage, id, uemail, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    /**
     * 通讯录更新
     *
     * @param subscriber
     * @param updateTime
     */
    public Flowable getContactUpdateData(FlowableSubscriber<BaseResponse<String>> subscriber, String updateTime) {
        Flowable flowable = mRxService.getContactUpdateData(updateTime, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    /**
     * 获取通讯录页面最近联系人
     *
     * @param subscriber
     */
    public Flowable getRecentContact(FlowableSubscriber<BaseResponse<ContactRecentBean>> subscriber, String time) {
        Flowable flowable = mRxService.getRecentContact(time, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    /**
     * getProcessList 获取流程列表
     *
     * @param subscriber
     */
    public Flowable getProcessList(FlowableSubscriber<BaseResponse<List<ProcessListBean>>> subscriber, String type) {
        Flowable flowable = mRxService.getProcessList(type, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * getProcessContent 获取发起流程的流程列表
     *
     * @param subscriber
     */
    public Flowable getProcessContent(FlowableSubscriber<BaseResponse<List<ProcessContentBean>>> subscriber, String processid) {
        Flowable flowable = mRxService.getProcessContent(processid, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * 获取用户填写详细内容
     * processUserDetail
     *
     * @param subscriber
     * @param pid
     */
    public Flowable processUserDetail(FlowableSubscriber<BaseResponse<List<ProcessUserDetailBean>>> subscriber, String pid) {
        Flowable flowable = mRxService.processUserDetail(pid, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    /**
     * 流程提交
     *
     * @param subscriber
     * @param processid
     * @param puid
     * @param title
     * @param pcondition_text
     * @return
     */
    public Flowable submitProcess(FlowableSubscriber<BaseResponse<String>> subscriber, String processid, String puid, String title, String pcondition_text) {
        Flowable flowable = mRxService.submitProcess(processid, puid, title, pcondition_text, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    /**
     * 获取已发起流程列表
     *
     * @param subscriber
     * @param type
     * @param keyword
     * @param page
     * @return
     */
    public Flowable getProcessAlReadyList(FlowableSubscriber<BaseResponse<List<ProcessAlReadyBean>>> subscriber, String type, String keyword, String page) {
        Flowable flowable = mRxService.getProcessAlReadyList(type, keyword, page, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }

    /**
     * 查询单个流程的进度
     *
     * @param subscriber
     * @param processid
     */
    public Flowable getProcessSchedule(FlowableSubscriber<BaseResponse<List<ProcessScheuleBean>>> subscriber, String processid) {
        Flowable flowable = mRxService.getProcessSchedule(processid, version);
        toSubscribe(flowable, subscriber);
        return flowable;
    }


    //******************************************************************************************************************************//
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

    private void toSubScribe(final String savePath, final String fileName, final SubDownload<File> subDownload, Flowable<ResponseBody> flowable) {
        flowable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(ResponseBody responseBody) throws Exception {
                        return subDownload.saveFile(responseBody, savePath, fileName);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subDownload);
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

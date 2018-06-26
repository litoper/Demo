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
import com.example.kadh.ui.work.bean.ProcessListBean;
import com.example.kadh.ui.work.bean.ProcessModuleBean;
import com.example.kadh.ui.work.bean.ProcessStatusBean;
import com.example.kadh.utils.RxJava.BaseResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

interface RxService {


    @GET(RxUrl.Url.CHECKVERSION)
    Observable<BaseResponse<String>> checkVersion(@Query("appVersion") String version);

    @FormUrlEncoded
    @POST(RxUrl.Url.LOGIN)
    Flowable<BaseResponse<List<LoginBean>>> login(
            @Field("name") String username,
            @Field("password") String password,
            @Field("version") String version);

    @GET(RxUrl.Url.GETUSERINFO)
    Flowable<BaseResponse<List<UserInfoBean>>> getUseInfo(
            @Query("userid") String userid,
            @Query("version") String version);

    @GET(RxUrl.Url.GETROLEMANAGESINGLE)
    Flowable<BaseResponse<List<RoleManageBean>>> getRoleManageSingle(
            @Query("userid") String userid,
            @Query("version") String version);


    @GET(RxUrl.Url.ISHASUNREAD)
    Flowable<BaseResponse<IsHasUnReadBean>> isHasUnRead(
            @Query("version") String version);

    @GET(RxUrl.Url.GETWEATHER)
    Flowable<BaseResponse<List<WeatherBean>>> getWeather(
            @Query("place") String place,
            @Query("version") String version);

    @GET(RxUrl.Url.GETPUBLISHLIST)
    Flowable<BaseResponse<List<PublishListBean>>> getPublishList(
            @Query("page") String page,
            @Query("ptype") String ptype,
            @Query("version") String version);


    @GET(RxUrl.Url.GETNOTICEDETAILBYPUBLISHID)
    Flowable<BaseResponse<List<PublishNoticeDetailBean>>> getNoticeDetailByPublishId(
            @Query("proPublishId") String proPublishId,
            @Query("version") String version);

    @GET(RxUrl.Url.GETPROCESSMODULELIST)
    Flowable<BaseResponse<List<ProcessModuleBean>>> getProcessModuleList(
            @Query("version") String version);

    @GET(RxUrl.Url.GETPROCESSSTATUS)
    Flowable<BaseResponse<List<ProcessStatusBean>>> getProcessStatus(
            @Query("version") String version);

    @GET(RxUrl.Url.QUERYPROCESSBASETOTALINFO)
    Flowable<BaseResponse<QueryProTotalInfoBean>> queryProcessBaseTotalInfo(
            @Query("version") String version);

    @GET(RxUrl.Url.GETNEWSDETAILBYPUBLISHID)
    Flowable<BaseResponse<List<PublishNewDetailBean>>> getNewsDetailByPublishId(
            @Query("proPublishId") String proPublishId,
            @Query("version") String version);

    @GET(RxUrl.Url.GETCOMMENTLIST)
    Flowable<BaseResponse<List<CommentListBean>>> getCommentList(
            @Query("page") String page,
            @Query("id") String id,
            @Query("ptype") String ptype,
            @Query("version") String version);

    @GET(RxUrl.Url.GETUPMANLIST)
    Flowable<BaseResponse<List<UpManListBean>>> getUpManList(
            @Query("page") String page,
            @Query("id") String id,
            @Query("ptype") String ptype,
            @Query("version") String version);

    @FormUrlEncoded
    @POST(RxUrl.Url.ADDCOMMENT)
    Flowable<BaseResponse<List<PublishNewDetailBean>>> addComment(
            @Field("ptype") String ptype,
            @Field("id") String id,
            @Field("remark_comment") String remark_comment,
            @Query("version") String version);

    @GET(RxUrl.Url.DELCOMMENT)
    Flowable<BaseResponse<String>> delComment(
            @Query("ptype") String ptype,
            @Query("remarkId") String remarkId,
            @Query("version") String version);

    @GET(RxUrl.Url.UPNUMBER)
    Flowable<BaseResponse<List<UpNumberBean>>> upNumber(
            @Query("ptype") String ptype,
            @Query("proPublishId") String proPublishId,
            @Query("uped") String uped, @Query("version") String version);

    @GET(RxUrl.Url.ISVALIDATE)
    Flowable<BaseResponse<isValiDateProcessPermitBean>> isVaLiDateProcessPermit(
            @Query("allowId") String allowId,
            @Query("version") String version);

    @GET(RxUrl.Url.MQREGISTER)
    Flowable<BaseResponse<String>> mqRegister(
            @Query("devicetoken") String devicetoken,
            @Query("fOS") String fOS,
            @Query("version") String version);

    @GET(RxUrl.Url.MQLOGOUT)
    Flowable<BaseResponse<String>> mqLogOut(
            @Query("devicetoken") String devicetoken,
            @Query("fOS") String fOS,
            @Query("version") String version);

    @GET(RxUrl.Url.MQPUSHMSGLIST)
    Flowable<BaseResponse<List<MessageBean>>> mqPushMsgList(
            @Query("pagesize") String pagesize,
            @Query("currentPage") String currentPage,
            @Query("state") String state,
            @Query("version") String version);

    @Streaming
    @GET
    Flowable<ResponseBody> download(@Url String url);


    @Multipart
    @POST(RxUrl.Url.UPFIELD)
    Flowable<BaseResponse<List<UpFieldBean>>> upField(@Part MultipartBody.Part file, @Query("version") String version, @Query("fileName") String filename);
    //    @GET(RxUrl.Url.CHECKVERSION + NICAI)
    //    Observable<BaseResponse<UpDateModel>> checkVersion(@Query("appVersion") String version);

    //    @FormUrlEncoded
    //    @POST(RxUrl.Url.UPDATEUPHONE + NICAI)
    //    Observable<BaseResponse<String>> upDateUphone(
    //            @Field("uphone") String uphone,
    //            @Field("ushort_phone") String ushort_phone,
    //            @Field("version") String version);

    //    @Multipart
    //    @POST(RxUrl.Url.UPLOADFIELD + NICAI)
    //    Observable<BaseResponse<List<UpFieldModel>>> upLoadField(@Part MultipartBody.Part file, @Query("version") String version, @Query("fileName") String filename);

    //    @Streaming
    //    @GET
    //    Observable<ResponseBody> download(@Url String url);


    @FormUrlEncoded
    @POST(RxUrl.Url.POSTUSERINFO)
    Flowable<BaseResponse<String>> postUserInfo(
            @Field("uimage") String uimage,
            @Field("urole") String id,
            @Field("uemail") String uemail,
            @Field("version") String version);

    @GET(RxUrl.Url.GETCONTACTUPDATEDATA)
    Flowable<BaseResponse<String>> getContactUpdateData(@Query("updateTime") String updateTime, @Query("version") String version);


    @GET(RxUrl.Url.GETRECENTCONTACT)
    Flowable<BaseResponse<ContactRecentBean>> getRecentContact(@Query("time") String time, @Query("version") String version);

    @GET(RxUrl.Url.GETPROCESSLIST)
    Flowable<BaseResponse<List<ProcessListBean>>> getProcessList(@Query("type") String type, @Query("version") String version);


}

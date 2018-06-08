package com.example.kadh.utils.RxJava.RxApi;


import com.example.kadh.bean.isValiDateProcessPermitBean;
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
import com.example.kadh.ui.message.MessageBean;
import com.example.kadh.ui.person.bean.QueryProTotalInfoBean;
import com.example.kadh.ui.person.bean.RoleManageBean;
import com.example.kadh.ui.work.bean.ProcessModuleBean;
import com.example.kadh.ui.work.bean.ProcessStatusBean;
import com.example.kadh.utils.RxJava.BaseResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

interface RxApiService {


    @GET(RxApiUrl.Url.CHECKVERSION)
    Observable<BaseResponse<String>> checkVersion(@Query("appVersion") String version);

    @FormUrlEncoded
    @POST(RxApiUrl.Url.LOGIN)
    Flowable<BaseResponse<List<LoginBean>>> login(
            @Field("name") String username,
            @Field("password") String password,
            @Field("version") String version);

    @GET(RxApiUrl.Url.GETUSERINFO)
    Flowable<BaseResponse<List<UserInfoBean>>> getUseInfo(
            @Query("userid") String userid,
            @Query("version") String version);

    @GET(RxApiUrl.Url.GETROLEMANAGESINGLE)
    Flowable<BaseResponse<List<RoleManageBean>>> getRoleManageSingle(
            @Query("userid") String userid,
            @Query("version") String version);


    @GET(RxApiUrl.Url.ISHASUNREAD)
    Flowable<BaseResponse<IsHasUnReadBean>> isHasUnRead(
            @Query("version") String version);

    @GET(RxApiUrl.Url.GETWEATHER)
    Flowable<BaseResponse<List<WeatherBean>>> getWeather(
            @Query("place") String place,
            @Query("version") String version);

    @GET(RxApiUrl.Url.GETPUBLISHLIST)
    Flowable<BaseResponse<List<PublishListBean>>> getPublishList(
            @Query("page") String page,
            @Query("ptype") String ptype,
            @Query("version") String version);


    @GET(RxApiUrl.Url.GETNOTICEDETAILBYPUBLISHID)
    Flowable<BaseResponse<List<PublishNoticeDetailBean>>> getNoticeDetailByPublishId(
            @Query("proPublishId") String proPublishId,
            @Query("version") String version);

    @GET(RxApiUrl.Url.GETPROCESSMODULELIST)
    Flowable<BaseResponse<List<ProcessModuleBean>>> getProcessModuleList(
            @Query("version") String version);

    @GET(RxApiUrl.Url.GETPROCESSSTATUS)
    Flowable<BaseResponse<List<ProcessStatusBean>>> getProcessStatus(
            @Query("version") String version);

    @GET(RxApiUrl.Url.QUERYPROCESSBASETOTALINFO)
    Flowable<BaseResponse<QueryProTotalInfoBean>> queryProcessBaseTotalInfo(
            @Query("version") String version);

    @GET(RxApiUrl.Url.GETNEWSDETAILBYPUBLISHID)
    Flowable<BaseResponse<List<PublishNewDetailBean>>> getNewsDetailByPublishId(
            @Query("proPublishId") String proPublishId,
            @Query("version") String version);

    @GET(RxApiUrl.Url.GETCOMMENTLIST)
    Flowable<BaseResponse<List<CommentListBean>>> getCommentList(
            @Query("page") String page,
            @Query("id") String id,
            @Query("ptype") String ptype,
            @Query("version") String version);

    @GET(RxApiUrl.Url.GETUPMANLIST)
    Flowable<BaseResponse<List<UpManListBean>>> getUpManList(
            @Query("page") String page,
            @Query("id") String id,
            @Query("ptype") String ptype,
            @Query("version") String version);

    @FormUrlEncoded
    @POST(RxApiUrl.Url.ADDCOMMENT)
    Flowable<BaseResponse<List<PublishNewDetailBean>>> addComment(
            @Field("ptype") String ptype,
            @Field("id") String id,
            @Field("remark_comment") String remark_comment,
            @Query("version") String version);

    @GET(RxApiUrl.Url.DELCOMMENT)
    Flowable<BaseResponse<String>> delComment(
            @Query("ptype") String ptype,
            @Query("remarkId") String remarkId,
            @Query("version") String version);

    @GET(RxApiUrl.Url.UPNUMBER)
    Flowable<BaseResponse<List<UpNumberBean>>> upNumber(
            @Query("ptype") String ptype,
            @Query("proPublishId") String proPublishId,
            @Query("uped") String uped, @Query("version") String version);

    @GET(RxApiUrl.Url.ISVALIDATE)
    Flowable<BaseResponse<isValiDateProcessPermitBean>> isVaLiDateProcessPermit(
            @Query("allowId") String allowId,
            @Query("version") String version);

    @GET(RxApiUrl.Url.MQREGISTER)
    Flowable<BaseResponse<String>> mqRegister(
            @Query("devicetoken") String devicetoken,
            @Query("fOS") String fOS,
            @Query("version") String version);

    @GET(RxApiUrl.Url.MQLOGOUT)
    Flowable<BaseResponse<String>> mqLogOut(
            @Query("devicetoken") String devicetoken,
            @Query("fOS") String fOS,
            @Query("version") String version);

    @GET(RxApiUrl.Url.MQPUSHMSGLIST)
    Flowable<BaseResponse<List<MessageBean>>> mqPushMsgList(
            @Query("pagesize") String pagesize,
            @Query("currentPage") String currentPage,
            @Query("state") String state,
            @Query("version") String version);


    //    @GET(RxApiUrl.Url.CHECKVERSION + NICAI)
    //    Observable<BaseResponse<UpDateModel>> checkVersion(@Query("appVersion") String version);

    //    @FormUrlEncoded
    //    @POST(RxApiUrl.Url.UPDATEUPHONE + NICAI)
    //    Observable<BaseResponse<String>> upDateUphone(
    //            @Field("uphone") String uphone,
    //            @Field("ushort_phone") String ushort_phone,
    //            @Field("version") String version);

    //    @Multipart
    //    @POST(RxApiUrl.Url.UPLOADFIELD + NICAI)
    //    Observable<BaseResponse<List<UpFieldModel>>> upLoadField(@Part MultipartBody.Part file, @Query("version") String version, @Query("fileName") String filename);

    //    @Streaming
    //    @GET
    //    Observable<ResponseBody> download(@Url String url);

}

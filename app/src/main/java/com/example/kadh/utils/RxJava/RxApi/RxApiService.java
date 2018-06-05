package com.example.kadh.utils.RxJava.RxApi;


import com.example.kadh.ui.company.bean.PublishListBean;
import com.example.kadh.ui.company.bean.PublishNoticeDetailBean;
import com.example.kadh.ui.login.bean.LoginBean;
import com.example.kadh.ui.main.bean.IsHasUnReadBean;
import com.example.kadh.ui.main.bean.UserInfoBean;
import com.example.kadh.ui.main.bean.WeatherBean;
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

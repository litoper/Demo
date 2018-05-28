package com.example.kadh.utils.RxJava.RxApi;


import com.example.kadh.utils.RxJava.BaseResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

interface RxApiService {


    @GET(RxConstant.Url.CHECKVERSION)
    Flowable<BaseResponse<String>> CheckVersion(@Query("appVersion") String version);

//    @GET(RxConstant.Url.CHECKVERSION + NICAI)
//    Observable<BaseResponse<UpDateModel>> CheckVersion(@Query("appVersion") String version);

//    @FormUrlEncoded
//    @POST(RxConstant.Url.UPDATEUPHONE + NICAI)
//    Observable<BaseResponse<String>> upDateUphone(
//            @Field("uphone") String uphone,
//            @Field("ushort_phone") String ushort_phone,
//            @Field("version") String version);

//    @Multipart
//    @POST(RxConstant.Url.UPLOADFIELD + NICAI)
//    Observable<BaseResponse<List<UpFieldModel>>> upLoadField(@Part MultipartBody.Part file, @Query("version") String version, @Query("fileName") String filename);

//    @Streaming
//    @GET
//    Observable<ResponseBody> download(@Url String url);

}

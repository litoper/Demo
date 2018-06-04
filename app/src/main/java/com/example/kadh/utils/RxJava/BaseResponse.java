package com.example.kadh.utils.RxJava;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */


public class BaseResponse<T> implements Serializable {

    @SerializedName("code")
    public Boolean code;

    @SerializedName("success")
    public Boolean success;

    @SerializedName("msg")
    public String msg;

    @SerializedName("total")
    public String total;

    @SerializedName("data")
    public T data;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", total='" + total + '\'' +
                ", data=" + data +
                '}';
    }
}

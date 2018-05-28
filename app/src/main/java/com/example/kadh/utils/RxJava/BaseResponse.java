package com.example.kadh.utils.RxJava;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by metaire on 2017/3/28.
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
}

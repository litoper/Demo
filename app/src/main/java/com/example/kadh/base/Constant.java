package com.example.kadh.base;

import com.example.kadh.app.App;
import com.example.kadh.utils.FileUtils;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class Constant {
    public static String PATH_ROOT = FileUtils.createRootPath(App.getApp());
    public static String PATH_CACHE = FileUtils.createRootPath(App.getApp()) + "/cache";
    public static String PATH_USER = FileUtils.createRootPath(App.getApp()) + "/user/";
}

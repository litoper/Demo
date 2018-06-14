package com.example.kadh.base;

import com.example.kadh.utils.AppUtils;
import com.example.kadh.utils.FileUtils;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/5/28
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */

public class Constant {
    public static String BASE_PATH = AppUtils.getAppContext().getCacheDir().getPath();
    public static String PATH_DATA = FileUtils.createRootPath(AppUtils.getAppContext()) + "/cache";

}

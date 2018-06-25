package com.example.kadh.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: kadh
 * @email : 36870855@qq.com
 * @date : 2018/6/25
 * @blog : http://www.nicaicaicai.com
 * @desc :
 */
public class RegularUtils {

    /**
     * 电子邮件验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isEmail(String str) {
//        Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(str);
        //Mather m = p.matcher("wangxu198709@gmail.com.cn");这种也是可以的！
        boolean b = m.matches();
        return b;
    }
}

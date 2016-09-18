package com.bk.fuliboom.Utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by Bk on 2016/8/29.
 */

public class DecodeUtil {
    public static String decodeUnicode2utf8(String source){
        String result = null;
        try {
            byte[] utf8 = source.getBytes("UTF-8");
            result = new String(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String decodeAccountString(String rawSource){
        Log.e("account",rawSource);
//        byte[] utf8 = new byte[0];
//        try {
//            utf8 = rawSource.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String result = new String(utf8);
//        String sub = result.substring(result.indexOf("帐号") + 3);
//        int point = sub.indexOf("密码");
//        return sub.substring(0,point);
        rawSource = rawSource.replaceAll("\\s","");
        String account =rawSource.split("帐号：")[1];
        account = account.split("密码：")[0];
        account = account.replaceAll("\\s","");
        return account;
    }

    public static String decodePassString(String rawSource){
        Log.e("psw", rawSource);
//        byte[] utf8 = new byte[0];
//        try {
//            utf8 = rawSource.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String result = new String(utf8);
//        String sub = result.substring(result.indexOf("帐号") + 3);
//        int point = sub.indexOf("密码");
//        return sub.substring(point + 3);
        rawSource = rawSource.replaceAll("\\s","");
        String psw =rawSource.split("密码：")[1];
        psw = psw.replaceAll("\\s","");
        return psw;
    }
}

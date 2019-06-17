package com.sandy.action;

import com.sandy.utils.HttpUtil;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RequestTokenAction
 * @Author sandy.n.hao
 * @Date: 2019-06-14
 * @Version v1.0.0
 * @Description: //TODO
 */

public class RequestTokenAction {

    static byte[] b;

    final static String OAUTH_REQUST_URI = "http://172.16.0.143:8011/oauth/token";

    public static String getToken(String account) throws NoSuchAlgorithmException, IOException {

        String token = null;
        String pwd_MD5 = string_MD5("111111");

        String str = "mro-server-app:d69de23d-1e83-4b82-860e-b880538c0d12";
        b = str.getBytes("utf-8");
        String app = new BASE64Encoder().encode(b);

        //请求头
        Map mapHeader = new HashMap<String, String>();
        mapHeader.put("Content-Type","application/x-www-form-urlencoded");
        mapHeader.put("Authorization","Basic " + app);

        //请求参数
        Map mapParam = new HashMap<String, String>();
        mapParam.put("grant_type", "password");
        mapParam.put("username", account);
        mapParam.put("password", pwd_MD5);

        //请求结果
        Map mapResp = new HashMap<String, String>();
        mapResp.put("access_token", null);
        mapResp.put("token_type", null);

        mapResp = HttpUtil.ExeHttpRequestByPost(OAUTH_REQUST_URI,mapHeader,mapParam,mapResp);

        token = mapResp.get("token_type") + " " + mapResp.get("access_token");

        return token;
    }

    public static String string_MD5(String pwd) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("md5");
        b = pwd.getBytes();
        byte[] digest = md5.digest(b);
        char[] chars = new char[]
                { '0', '1', '2', '3', '4', '5','6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
        StringBuffer stringBuffer = new StringBuffer();
        for (byte bb : digest) {
            stringBuffer.append(chars[(bb >> 4) & 15]);
            stringBuffer.append(chars[bb & 15]);
        }

        return stringBuffer.toString().toLowerCase();

    }

}

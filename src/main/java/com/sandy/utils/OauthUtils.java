package com.sandy.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @ClassName: OauthUtils
 * @Author sandy.n.hao
 * @Date: 2019-06-14
 * @Version v1.0.0
 * @Description: //TODO
 */

public class OauthUtils {


    public static String getToken(String account) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String line;
        JSONObject resultJsonObject;
        StringBuilder entityStringBuilder = new StringBuilder();
        byte[] b;
        String pwd = "111111";
        Map res = new HashMap<String, String>();;

        //BASE64编码
        String str = "mro-server-app:d69de23d-1e83-4b82-860e-b880538c0d12";
        b = str.getBytes("utf-8");
        String app = new BASE64Encoder().encode(b);

//        //MD5密码加密
//        MessageDigest md5 = MessageDigest.getInstance("md5");
//        b = pwd.getBytes();
//        byte[] digest = md5.digest(b);
//        char[] chars = new char[]
//                { '0', '1', '2', '3', '4', '5','6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
//
//        StringBuffer stringBuffer = new StringBuffer();
//        for (byte bb : digest) {
//            stringBuffer.append(chars[(bb >> 4) & 15]);
//            stringBuffer.append(chars[bb & 15]);
//        }


//        HttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://172.16.0.143:8011/oauth/token");
//        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
//        httpPost.setHeader("Authorization", "Basic " + app);

//        Map map = new HashMap<String, String>();
//        map.put("grant_type", "password");
//        map.put("username", account);
//        map.put("password", stringBuffer.toString().toLowerCase());

//        try {
//
//            List<NameValuePair> list = new ArrayList();
//
//            Iterator iterator = map.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
//                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
//            }
//            if (list.size() > 0) {
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
//                httpPost.setEntity(entity);
//            }
//
//            HttpResponse response = httpClient.execute(httpPost);
//
//            if (response.getStatusLine().getStatusCode() == 200) {
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"), 8 * 1024);
//                while ((line = bufferedReader.readLine()) != null) {
//                    entityStringBuilder.append(line);
//                }
//                resultJsonObject = new JSONObject().parseObject(entityStringBuilder.toString());
//                res.put("access_token", resultJsonObject.getString("access_token"));
//                res.put("token_type", resultJsonObject.getString("token_type"));
//            } else {
//                res.put("access_token", "");
//                res.put("token_type","");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return (res.get("access_token").toString()+" "+res.get("token_type").toString());
    }
}

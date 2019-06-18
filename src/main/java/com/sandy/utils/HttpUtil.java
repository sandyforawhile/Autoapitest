package com.sandy.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Logger;


/**
 * @ClassName: HttpUtil
 * @Author sandy.n.hao
 * @Date: 2019-03-11
 * @Version v1.0.0
 * @Description: //TODO
 */

public class HttpUtil {

    public final static String HOST = "http://172.16.0.143:8010";
    public final static String GATEWAY_ROUTE = "/api/order";
    public final static String URI = HOST + GATEWAY_ROUTE + "/v1/";

    public static Logger logger = Logger.getLogger(LoginException.class.getName());

    public static String str;

    public static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public static void PackHttpReqBody(HttpPost httpPost, Map mapParam){

        try {
            List<NameValuePair> list = new ArrayList();
            if(mapParam != null){
                Iterator iterator = mapParam.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                    list.add(new BasicNameValuePair(elem.getKey(), String.valueOf(elem.getValue())));
                }
                if (list.size() > 0) {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
                    httpPost.setEntity(entity);
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.warning("HttpPost Entity 设置失败：" + e.toString());
        }
    }

    public static void PackHttpReqHeader(HttpRequestBase httpReq, Map mapHeader) {

        Iterator iterator = mapHeader.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
            httpReq.setHeader(elem.getKey(), elem.getValue());
        }
    }

    public static Map<String, String> AnalysisResponse(HttpResponse response, Map mapResp) throws IOException {

        Map<String,String> mapRespSplit = new HashMap<>();

        String line;
        StringBuilder entityStringBuilder = new StringBuilder();
        JSONObject resultJsonObject;


        if (response.getEntity() != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"), 8 * 1024);
            while ((line = bufferedReader.readLine()) != null) {
                entityStringBuilder.append(line);
            }
            try {
                resultJsonObject = new JSONObject().parseObject(entityStringBuilder.toString());
                Iterator iterator = mapResp.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                    String key = elem.getKey();
                    mapRespSplit.put(key, resultJsonObject.getString(key));
                }
            }catch (Exception e)
            {
            }
        }
        if (response.getStatusLine().getStatusCode() < 300 ){
            mapRespSplit.put("description","success");
        }


        mapRespSplit.put("code",String.valueOf(response.getStatusLine().getStatusCode()));


        return mapRespSplit;
    }

    public static String ExeHttpRequestByPost(String domain, String method, String token, String requestbody, String business) {


//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpClient httpClient = new DefaultHttpClient();

        Map<String,String> mapHeader = new HashMap<>();
        Map<String, Object> mapReqBody;
        Map<String, String> mapRespSplit;

        Map<String, String> mapRespNeed = new HashMap<>();
        mapRespNeed.put("code",null);
        mapRespNeed.put("description",null);


        try {
            HttpPost httpPost = new HttpPost(URI + domain + "/" + business + "/" + method);

            if (method.equals("export")) {
                mapHeader.put("Content-Type", "application/x-www-form-urlencoded");
                mapReqBody = JsonUtil.Json2Map(requestbody);
                HttpUtil.PackHttpReqHeader(httpPost, mapHeader);
                HttpUtil.PackHttpReqBody(httpPost, mapReqBody);
            } else {
                mapHeader.put("Content-Type", "application/json");
                if (token != null) {
                    mapHeader.put("Authorization", token);
                }
                HttpUtil.PackHttpReqHeader(httpPost, mapHeader);
                if (requestbody != null && !requestbody.equals("")) {
                    StringEntity stringEntity = new StringEntity(requestbody);
                    httpPost.setEntity(stringEntity);
                }
            }

            HttpResponse response = httpClient.execute(httpPost);
            mapRespSplit = HttpUtil.AnalysisResponse(response,mapRespNeed);

            if (mapRespSplit.size() == 1)
                logger.warning(domain + "." + business + "." + method + "方法解析错误，没有获取到所需的返回值。");

            str = ReportUtil.InsertReport(business, domain, method, mapRespSplit.get("code"), mapRespSplit.get("description"), "POST");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public static Map ExeHttpRequestByPost(String oauthRequestUri, Map map_header, Map map_param, Map mapRespNeed) throws IOException {

        Map<String,String> mapRespSplit;

//        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(oauthRequestUri);

        HttpUtil.PackHttpReqHeader(httpPost,map_header);
        HttpUtil.PackHttpReqBody(httpPost,map_param);

        HttpResponse response = httpClient.execute(httpPost);

        mapRespSplit = HttpUtil.AnalysisResponse(response,mapRespNeed);

        return mapRespSplit;
    }

    public static String ExeHttpRequestByGet(String domain, String method, String token, Object object, String business) {

        Map<String,String> mapHeader = new HashMap<>();
        Map<String, String> mapRespSplit;
        Map<String, String> mapRespNeed = new HashMap<>();
        mapRespNeed.put("code",null);
        mapRespNeed.put("description",null);

//        HttpClient httpClient = new DefaultHttpClient();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {

            HttpGet httpGet = new HttpGet(URI + domain + "/" + business + "/" + method + "/" + object);

            mapHeader.put("Content-Type", "application/json");
            if(token != null){
                mapHeader.put("Authorization", token);
            }
            HttpUtil.PackHttpReqHeader(httpGet, mapHeader);

            HttpResponse response = httpClient.execute(httpGet);
            mapRespSplit = HttpUtil.AnalysisResponse(response,mapRespNeed);


            str = ReportUtil.InsertReport(business, domain, method, mapRespSplit.get("code"), mapRespSplit.get("description"), "GET");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}

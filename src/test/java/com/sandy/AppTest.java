package com.sandy;

import static org.junit.Assert.assertTrue;

import com.sandy.action.RequestTokenAction;
import com.sandy.action.TestCaseAction;
import com.sandy.utils.*;
import org.junit.Test;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void ApiTest() throws IOException, NoSuchAlgorithmException {

        final String JSON_CASE_FILE_PATH = "src/main/resources/json/";
        final String PROPERTIES_REQUEST_METHOD_FILE_PATH = "/common/requestMethod.properties";
        final String PROPERTIES_ACCOUNT_FILE_PATH = "/common/account.properties";

        Properties accountProp = PropertiesUtil.PropLoader(PROPERTIES_ACCOUNT_FILE_PATH);
        Properties modeProp = PropertiesUtil.PropLoader(PROPERTIES_REQUEST_METHOD_FILE_PATH);

        Logger logger = Logger.getLogger(LoginException.class.getName());

        //装载默认测试场景
        Map<Integer, String> testcases = TestCaseAction.InitializationCasesSet();

        //定义用例执行业务
        String business = "trade";

        //定义要执行的用例场景
        int[] exe_cases = new int[]{1};

        String account;
        String token;
        String domain;
        String method;
        String mode = null;
        String content;

        String response = null;
        Object param = null;
        Map<String, Object> map;

        //执行用例&生成测试报告
        for (int i = 0; i < exe_cases.length; i++) {

            int index = exe_cases[i];

            //根据用例场景获取请求授权码
            account = accountProp.getProperty(testcases.get(index));
            token = RequestTokenAction.getToken(account);

            String fileName = business.toUpperCase() + "_" + testcases.get(index);

            //生成测试报告文件
            File fileReport = FileUtil.CreateFile("report/", fileName + "_接口测试报告", "html");

            //打包测试报告内容
            ReportUtil.PackReport(fileReport, fileName);

            //读取用例文件
            List<File> fileList = FileUtil.GetAllFileName(JSON_CASE_FILE_PATH + business);


            if (fileList.size() > 0) {

                //执行测试用例
                for (File fileCase : fileList) {

                    String[] fileInfo = fileCase.getName().split("\\.");

                    domain = fileInfo[0];
                    method = fileInfo[1];
                    content = FileUtil.ReadFile(fileCase.getAbsolutePath());

                    if (index == 8) {
                        //构造Correct_Token
                        switch (fileInfo[0]) {
                            case "admin":
                                token = RequestTokenAction.getToken(accountProp.getProperty(testcases.get(5)));
                                break;
                            case "purchaser":
                                token = RequestTokenAction.getToken(accountProp.getProperty(testcases.get(3)));
                                break;
                            case "supplier":
                                token = RequestTokenAction.getToken(accountProp.getProperty(testcases.get(1)));
                                break;
                            default:
                                token = null;
                        }
                    }

                    if (modeProp.getProperty(domain + "." + method) == null) {
                        logger.warning("HTTP请求方式未设置：" + method);
                    } else {
                        mode = modeProp.getProperty(domain + "." + method).toLowerCase();
                    }

                    if (mode.equals("post")) {
                        response = HttpUtil.ExeHttpRequestByPost(domain, method, token, content, business);
                    } else if (mode.equals("get")) {
                        map = JsonUtil.Json2Map(content);
                        if (map != null) {
                            param = map.get("param");
                        }
                        response = HttpUtil.ExeHttpRequestByGet(domain, method, token, param, business);
                    } else {
                        logger.warning("HTTP请求方式不正确：" + method);
                    }

                }
            }

            ReportUtil.PackReport(fileReport, "");
            ReportUtil.GeneReport(fileReport, response);

        }
    }

}

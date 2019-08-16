package com.sandy;

import com.sandy.action.RequestTokenAction;
import com.sandy.action.TestCaseAction;
import com.sandy.utils.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @ClassName: BaseTest
 * @Author sandy.n.hao
 * @Date: 2019-06-17
 * @Version v1.0.0
 * @Description: //TODO
 */

public class BaseTest {

    final String JSON_CASE_FILE_PATH = "src/main/resources/json/";
    final String PROPERTIES_REQUEST_METHOD_FILE_PATH = "/common/requestMethod.properties";
    final String PROPERTIES_ACCOUNT_FILE_PATH = "/common/account.properties";

    Properties accountProp = PropertiesUtil.propLoader(PROPERTIES_ACCOUNT_FILE_PATH);
    Properties modeProp = PropertiesUtil.propLoader(PROPERTIES_REQUEST_METHOD_FILE_PATH);
    Logger logger = Logger.getLogger(LoginException.class.getName());

    //装载默认测试场景
    Map<Integer, String> testcases = TestCaseAction.InitializationCasesSet();

    String response = null;
    String mode = null;
    String content;
    String domain;
    String method;
    Object param = null;
    String token;
    Map<String, Object> map;
    String authString;

    File fileReport;
    static File fileIndex;
    String fileName;

    SimpleDateFormat simpleDateFormat;

    public void exeCase(int tokenIndex, String business, String user, boolean authFlag) throws IOException, NoSuchAlgorithmException {

        token = RequestTokenAction.getToken(accountProp.getProperty(testcases.get(tokenIndex)));

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date) + "_";

        if(authFlag == true){
            fileName = str + user.toUpperCase() + "_" + business.toUpperCase() + "_" + "已授权";
            authString = "是";
        }
        else {
            fileName = str + user.toUpperCase() + "_" + business.toUpperCase() + "_" + "未授权";
            authString = "否";
        }

        //生成测试报告文件
        fileReport = FileUtil.createFile("report/", fileName + "_接口测试报告", "html");
        ReportUtil.packReport(fileReport, fileName);

        //读取用例文件
        List<File> fileList = FileUtil.getAllFileName(JSON_CASE_FILE_PATH + business);


        if (fileList.size() > 0) {

            //执行测试用例
            for (File fileCase : fileList) {

                String[] fileInfo = fileCase.getName().split("\\.");

                domain = fileInfo[0];
                method = fileInfo[1];
                content = FileUtil.readFile(fileCase.getAbsolutePath());

                if (modeProp.getProperty(domain + "." + method) == null) {
                    logger.warning("HTTP请求方式未设置：" + method);
                } else {
                    mode = modeProp.getProperty(domain + "." + method).toLowerCase();
                }

                if (mode.equals("post")) {
                    response = HttpUtil.exeHttpRequestByPost(domain, method, token, content, business,authString);
                } else if (mode.equals("get")) {
                    map = JsonUtil.json2Map(content);
                    if (map != null) {
                        param = map.get("param");
                    }
                    response = HttpUtil.exeHttpRequestByGet(domain, method, token, param, business,authString);
                } else {
                    logger.warning("HTTP请求方式不正确：" + method);
                }

            }
            ReportUtil.insertReport(fileReport);
            ReportUtil.packReport(fileReport, "");
            ReportUtil.insertReport(fileIndex,"<a href=\""+fileName+"_接口测试报告.html\">"+fileName+"_接口测试报告.html</a><br>");

        }

        fileList.clear();
    }

    @BeforeClass
    public static void init() throws IOException {
        fileIndex = FileUtil.createFile("report/", "index", "html");
        ReportUtil.packIndex(fileIndex);
    }

    @AfterClass
    public static void finish() throws IOException {
        ReportUtil.packIndex(fileIndex);
    }

}

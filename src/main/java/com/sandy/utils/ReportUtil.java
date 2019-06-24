package com.sandy.utils;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName: ReportUtil
 * @Author sandy.n.hao
 * @Date: 2019-03-12
 * @Version v1.0.0
 * @Description: //TODO
 */

public class ReportUtil {

    static StringBuffer stringBuffer = new StringBuffer();

    public static void packReport(File file,String title) throws IOException {

        StringBuffer str = new StringBuffer();

        if(file.length() == 0){
            str.delete(0,str.length());
            str.append("<html>")
                    .append("<head>")
                    .append("<meta charset=\"utf-8\">")
                    .append("<title>").append(title).append("</title>")
                    .append("<link rel='stylesheet' href='index.css'></link>")
                    .append("</head>")
                    .append("<body>")
                    .append("<h1>").append(title).append("_测试结果").append("</h1>")
                    .append("<table>")
                    .append("<thead>")
                    .append("<tr>")
                    .append("<th width='100px'>业务</th>")
                    .append("<th width='100px'>访问域</th>")
                    .append("<th width='200px'>请求方法</th>")
                    .append("<th width='100px'>是否授权</th>")
                    .append("<th width='70px'>请求方式</th>")
                    .append("<th width='110px'>请求返回码</th>")
                    .append("<th>描述信息</th>")
                    .append("</tr>")
                    .append("</thead>")
                    .append("<tbody>");
        }
        else {
            str.append("</tbody>")
                    .append("</table>")
                    .append("</body>")
                    .append("</html>");
        }

        FileUtil.writeFile(file, str.toString());
    }

    public static String geneCaseReport(String business, String domain, String method, String code,String des ,String mode, String authFlag) {

        if(!code.isEmpty())
        {
            stringBuffer.append("<tr>")
                    .append("<td>").append(business).append("</td>")
                    .append("<td>").append(domain).append("</td>")
                    .append("<td>").append(method).append("</td>")
                    .append("<td>").append(authFlag).append("</td>")
                    .append("<td>").append(mode).append("</td>");
            if (Integer.valueOf(code) >= 300){
                stringBuffer.append("<td style='color:red'>").append(code).append("</td>");
            } else {
                stringBuffer.append("<td style='color:green'>").append(code).append("</td>");
            }
            stringBuffer.append("<td>").append(des).append("</td>")
                    .append("</tr>");
        }
        return stringBuffer.toString();
    }

    public static void insertReport(File file, String strContext) throws IOException {
        FileUtil.writeFile(file, strContext);
    }

    public static void packIndex(File fileIndex) throws IOException {

        StringBuffer str = new StringBuffer();

        if(fileIndex.length() == 0){
            str.append("<html>")
                    .append("<head>")
                    .append("<meta charset=\"utf-8\">")
                    .append("<title>").append("测试结果集").append("</title>")
                    .append("<link rel='stylesheet' href='index.css'></link>")
                    .append("</head>")
                    .append("<body>");
        }
        else {
            str.append("</body>")
                    .append("</html>");
        }

        FileUtil.writeFile(fileIndex, str.toString());
    }

    public static void insertReport(File file) throws IOException {
        FileUtil.writeFile(file, stringBuffer.toString());
        stringBuffer.setLength(0);
    }
}

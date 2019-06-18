package com.sandy.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: FileUtil
 * @Author sandy.n.hao
 * @Date: 2019-03-12
 * @Version v1.0.0
 * @Description: //TODO
 */

public class FileUtil {

    public static List<File> fileList = new ArrayList<>();
    public static final String FILE_PATH = "src/main/resources/";

    //读取文件
    public static String ReadFile(String filename){

        int len=0;
        StringBuffer str = new StringBuffer();
        try {

                FileReader fr = new FileReader(""+filename);
                BufferedReader br = new BufferedReader(fr);
                String line;

                while( (line=br.readLine())!=null )
                {
                    if(len != 0)  // 处理换行符的问题
                    {
                        str.append(line);
                    }
                    else
                    {
                        str.append(line);
                    }
                    len++;
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString().trim();
    }

    //写文件
    public static void WriteFile(File filename, String context) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(context);
        bw.flush();
    }

    //新建文件
    public static File CreateFile(String filepath, String filename, String suffix){

//        SimpleDateFormat simpleDateFormat;
//        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();
//        String str = simpleDateFormat.format(date) + "_";
        File file = null;


        file = new File(FILE_PATH  + filepath + filename + "." + suffix);



        try {
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }

        return file;
    }

    //递归获取路径下全部文件名
    public static List<File> GetAllFileName(String filepath){

        File file = new File(filepath);
        File[] files = file.listFiles();

        if(files == null)
            return null;

        for (File f: files) {
            if(f.isFile()){
                fileList.add(f);
            }
            else if(f.isDirectory()){
                GetAllFileName(f.getAbsolutePath());
            }
        }

        return fileList;
    }

}



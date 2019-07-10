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
    public static String readFile(String filename){

        StringBuffer str = new StringBuffer();
        try {

                FileReader fr = new FileReader(""+filename);
                BufferedReader br = new BufferedReader(fr);
                String line;

                while( (line=br.readLine())!=null )
                {
                    str.append(line);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString().trim();
    }

    //写文件
    public static void writeFile(File filename, String context) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(context);
        bw.flush();
    }

    //新建文件
    public static File createFile(String filepath, String filename, String suffix){

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
    public static List<File> getAllFileName(String filepath){

        File file = new File(filepath);
        File[] files = file.listFiles();

        if(files == null)
            return null;

        for (File f: files) {
            if(f.isFile()){
                fileList.add(f);
            }
            else if(f.isDirectory()){
                getAllFileName(f.getAbsolutePath());
            }
        }

        return fileList;
    }

}



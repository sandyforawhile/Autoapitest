package com.sandy.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @ClassName: PropertiesUtil
 * @Author sandy.n.hao
 * @Date: 2019-03-12
 * @Version v1.0.0
 * @Description: //TODO
 */

public class PropertiesUtil {

    public static Properties PropLoader(String fileName){

        Properties prop = new Properties();

        try{
            InputStream inputStream =Object.class.getResourceAsStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            prop.load(bufferedReader);
        }catch(Exception e){
            System.out.println(e);
        }
        return prop;
    }
}

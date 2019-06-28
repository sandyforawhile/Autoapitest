package com.sandy.action;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TestCaseAction
 * @Author sandy.n.hao
 * @Date: 2019-06-14
 * @Version v1.0.0
 * @Description: //TODO
 */

public class TestCaseAction {

    //初始化用例集合
    public static Map<Integer,String> InitializationCasesSet(){

        Map<Integer, String> testcases = new HashMap<>();
        testcases.put(1,"FullRoute_SupplierToken_已授权");
        testcases.put(2,"FullRoute_SupplierToken_未授权");
        testcases.put(3,"FullRoute_PurchaserToken_已授权");
        testcases.put(4,"FullRoute_PurchaserToken_未授权");
        testcases.put(5,"FullRoute_AdminToken_已授权");
        testcases.put(6,"FullRoute_AdminToken_未授权");
        testcases.put(7,"FullRoute_EmptyToken_未授权");
//        testcases.put(8,"FullRoute_CorrectToken_已授权");

        return testcases;
    }



}

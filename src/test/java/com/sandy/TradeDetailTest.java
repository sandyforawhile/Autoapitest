package com.sandy;

import com.sandy.action.RequestTokenAction;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: TradeDetailTest
 * @Author sandy.n.hao
 * @Date: 2019-06-17
 * @Version v1.0.0
 * @Description: //TODO
 */

public class TradeDetailTest  extends BaseTest {

    String business = "tradeDetail";
    String token;

    @Test
    public void TradeDetailAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        token = RequestTokenAction.getToken(accountProp.getProperty(testcases.get(1)));
        exeCase(1, business,"Supplier",true);

    }
}

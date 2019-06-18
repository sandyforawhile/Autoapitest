package com.sandy;

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

public class TradeDetailTest extends BaseTest {

    String business = "tradeDetail";

    @Test
    public void TradeDetailAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(1, business,"Supplier",true);
    }
}

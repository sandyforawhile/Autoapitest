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

    @Test
    public void TradeDetailAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(3, business,"Purchaser",true);
    }

    @Test
    public void TradeDetailAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(5, business,"Admin",true);
    }

    @Test
    public void TradeDetailUnAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(2, business,"Supplier",false);
    }

    @Test
    public void TradeDetailUnAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(4, business,"Purchaser",false);
    }

    @Test
    public void TradeDetailUnAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(6, business,"Admin",false);
    }

    @Test
    public void TradeDetailUnAuthTest() throws IOException, NoSuchAlgorithmException {
        exeCase(7, business,"Empty",false);
    }
}

package com.sandy;

import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: TradeReturnTest
 * @Author sandy.n.hao
 * @Date: 2019-06-18
 * @Version v1.0.0
 * @Description: //TODO
 */

public class TradeReturnTest extends BaseTest {

    String business = "tradeReturn";

    @Test
    public void TradeReturnAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(1, business,"Supplier",true);
    }

    @Test
    public void TradeReturnAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(3, business,"Purchaser",true);
    }

    @Test
    public void TradeReturnAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(5, business,"Admin",true);
    }

    @Test
    public void TradeReturnUnAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(2, business,"Supplier",false);
    }

    @Test
    public void TradeReturnUnAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(4, business,"Purchaser",false);
    }

    @Test
    public void TradeReturnUnAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(6, business,"Admin",false);
    }

    @Test
    public void TradeReturnUnAuthTest() throws IOException, NoSuchAlgorithmException {
        exeCase(7, business,"Empty",false);
    }
}

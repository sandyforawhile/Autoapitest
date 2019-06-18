package com.sandy;

import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: TradeDeliveryTest
 * @Author sandy.n.hao
 * @Date: 2019-06-18
 * @Version v1.0.0
 * @Description: //TODO
 */

public class TradeDeliveryTest extends BaseTest{

    String business = "tradeDelivery";

    @Test
    public void TradeDeliveryAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(1, business,"Supplier",true);
    }

    @Test
    public void TradeDeliveryAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(3, business,"Supplier",true);
    }

    @Test
    public void TradeDeliveryAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(5, business,"Supplier",true);
    }

    @Test
    public void TradeDeliveryUnAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(2, business,"Supplier",false);
    }

    @Test
    public void TradeDeliveryUnAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(4, business,"Supplier",false);
    }

    @Test
    public void TradeDeliveryUnAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(6, business,"Supplier",false);
    }

    @Test
    public void TradeDeliveryUnAuthTest() throws IOException, NoSuchAlgorithmException {
        exeCase(7, business,"Admin",false);
    }
}

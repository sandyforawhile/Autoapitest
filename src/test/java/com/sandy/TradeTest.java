package com.sandy;

import org.junit.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class TradeTest extends BaseTest {

    String business = "trade";
    String token;

    @Test
    public void TradeAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(1,business,"Supplier",true);
    }

    @Test
    public void TradeAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(3, business,"Purchaser",true);
    }

    @Test
    public void TradeAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(5, business,"Admin",true);
    }

    @Test
    public void TradeUnAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(2, business,"Supplier",false);
    }

    @Test
    public void TradeUnAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(4, business,"Purchaser",false);
    }

    @Test
    public void TradeUnAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(6, business,"Admin",false);
    }

}

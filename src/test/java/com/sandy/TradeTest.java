package com.sandy;

import org.junit.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class TradeTest extends BaseTest {

    String business = "trade";

    @Test
    public void tradeAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(1, business,"Supplier",true);
    }

    @Test
    public void tradeAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(3, business,"Purchaser",true);
    }

    @Test
    public void tradeAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(5, business,"Admin",true);
    }

    @Test
    public void tradeUnAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(2, business,"Supplier",false);
    }

    @Test
    public void tradeUnAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(4, business,"Purchaser",false);
    }

    @Test
    public void tradeUnAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(6, business,"Admin",false);
    }

    @Test
    public void tradeUnAuthTest() throws IOException, NoSuchAlgorithmException {
        exeCase(7, business,"Empty",false);
    }
}

package com.sandy;

import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: ShoppingCartTest
 * @Author sandy.n.hao
 * @Date: 2019-06-18
 * @Version v1.0.0
 * @Description: //TODO
 */

public class ShoppingCartTest extends BaseTest{

    String business = "shoppingCart";

    @Test
    public void shoppingCartAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(1, business,"Supplier",true);
    }

    @Test
    public void shoppingCartAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(3, business,"Purchaser",true);
    }

    @Test
    public void shoppingCartAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(5, business,"Admin",true);
    }

    @Test
    public void shoppingCartUnAuthSupplierTest() throws IOException, NoSuchAlgorithmException {
        exeCase(2, business,"Supplier",false);
    }

    @Test
    public void shoppingCartUnAuthPurchaserTest() throws IOException, NoSuchAlgorithmException {
        exeCase(4, business,"Purchaser",false);
    }

    @Test
    public void shoppingCartUnAuthAdminTest() throws IOException, NoSuchAlgorithmException {
        exeCase(6, business,"Admin",false);
    }

    @Test
    public void shoppingCartUnAuthTest() throws IOException, NoSuchAlgorithmException {
        exeCase(7, business,"Empty",false);
    }

}

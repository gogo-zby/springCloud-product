package com.hmily.product.service;

import com.hmily.product.ProductApplicationTests;
import com.hmily.product.dataobject.ProductInfo;
import com.hmily.product.dto.CartDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertTrue(upAll.size() == 2);
    }

    @Test
    public void findByProductIdIn() throws Exception {
        List<ProductInfo> list = productService.findByProductIdIn(Arrays.asList("157875196366160022", "157875227953464068"));
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void decreaseStock() throws Exception {
        CartDTO decreaseStockInput = new CartDTO("157875196366160022", 2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }
}
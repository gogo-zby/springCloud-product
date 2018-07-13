package com.hmily.product.service;

import com.hmily.product.ProductApplicationTests;
import com.hmily.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
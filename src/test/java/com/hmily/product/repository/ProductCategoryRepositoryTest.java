package com.hmily.product.repository;

import com.hmily.product.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Test
    public void findByCategoryTypeIn() throws Exception {
         List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(byCategoryTypeIn.size() > 0);
    }

}
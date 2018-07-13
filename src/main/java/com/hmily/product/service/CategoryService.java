package com.hmily.product.service;

import com.hmily.product.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}

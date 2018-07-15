package com.hmily.product.service.impl;

import com.hmily.product.common.DecreaseStockInput;
import com.hmily.product.common.ProductInfoOutput;
import com.hmily.product.dataobject.ProductInfo;
import com.hmily.product.enums.ProductStatusEnum;
import com.hmily.product.repository.ProductInfoRepository;
import com.hmily.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }
}

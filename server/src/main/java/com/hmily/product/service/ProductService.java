package com.hmily.product.service;

import com.hmily.product.common.DecreaseStockInput;
import com.hmily.product.common.ProductInfoOutput;
import com.hmily.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}

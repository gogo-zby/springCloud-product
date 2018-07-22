package com.hmily.product.service;

import com.hmily.product.dataobject.ProductInfo;
import com.hmily.product.dto.CartDTO;
import org.springframework.web.bind.annotation.RequestBody;

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
    List<ProductInfo> findByProductIdIn(List<String> productIdList);

    /**
     * 扣库存
     * @param cartDTOList
     */
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}

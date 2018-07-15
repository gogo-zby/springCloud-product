package com.hmily.product.client;

import com.hmily.product.common.DecreaseStockInput;
import com.hmily.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")   //有RequestBody注解就得使用post
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}

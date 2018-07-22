package com.hmily.product.client;


import com.hmily.product.common.DecreaseStockInput;
import com.hmily.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Component;
import java.util.List;


@FeignClient(name = "product", fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/msg") //要调用接口的具体URL
    String productMsg();    //这里的方法名可自由定义，上面的URL和对应服务的URL一致即可

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @Component  //注意了，下面这些就是服务降级时返回的结果（）
    static class ProductClientFallback implements ProductClient {
        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }
        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
        @Override
        public String productMsg() {
            return "服务降级提示";
        }
    }
}

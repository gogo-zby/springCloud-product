package com.hmily.product.service.impl;

import com.hmily.product.common.DecreaseStockInput;
import com.hmily.product.common.ProductInfoOutput;
import com.hmily.product.dataobject.ProductInfo;
import com.hmily.product.enums.ProductStatusEnum;
import com.hmily.product.enums.ResultStatusEnum;
import com.hmily.product.exception.ProductException;
import com.hmily.product.repository.ProductInfoRepository;
import com.hmily.product.service.ProductService;
import com.hmily.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findByProductIdIn(List<String> productIdList) {
//        return productInfoRepository.findByProductIdIn(productIdList);
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);

        //发送mq消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));

//        for (CartDTO cartDTO: cartDTOList){
//            //判断商品是否存在
//            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
//            if(!productInfoOptional.isPresent()){
//                throw new ProductException(ResultStatusEnum.PRODUCT_NOT_EXIST);
//            }
//            //库存是否足够
//            ProductInfo productInfo = productInfoOptional.get();
//            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
//            if(result < 0){
//                throw new ProductException(ResultStatusEnum.PRODUCT_STOCK_ERROR);
//            }
//            productInfo.setProductStock(result);
//            productInfoRepository.save(productInfo);
//        }
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()){
                throw new ProductException(ResultStatusEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            //库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultStatusEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}

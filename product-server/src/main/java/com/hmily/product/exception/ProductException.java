package com.hmily.product.exception;

import com.hmily.product.enums.ResultStatusEnum;

public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public ProductException(ResultStatusEnum resultStatusEnum){
        super(resultStatusEnum.getMessage());
        this.code = resultStatusEnum.getCode();
    }
}

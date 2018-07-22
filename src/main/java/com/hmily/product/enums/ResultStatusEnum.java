package com.hmily.product.enums;

import lombok.Getter;

@Getter
public enum ResultStatusEnum {
    SUCCESS(0, "SUCCESS"),
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存不足"),
    ERROR(-1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(-2, "ILLEGAL_ARGUMENT");

    private Integer code;
    private String message;

    ResultStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

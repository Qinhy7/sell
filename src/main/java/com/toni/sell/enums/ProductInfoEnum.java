package com.toni.sell.enums;

import lombok.Getter;

/**
 * 商品信息上下架 枚舉類
 */
@Getter
public enum ProductInfoEnum implements CodeEnum {
    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    private ProductInfoEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}

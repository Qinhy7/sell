package com.toni.sell.enums;

import lombok.Getter;

/**
 * 支付状态枚举
 */
@Getter
public enum PayStatusEnums implements CodeEnum {

    NEW(0 , "未支付"),
    FINISH(1 , "已支付"),
    ;

    private Integer code;

    private String msg;

    PayStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

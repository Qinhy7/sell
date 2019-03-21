package com.toni.sell.exception;

import com.toni.sell.enums.ExceptionCodeEnums;
import lombok.Getter;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 10:27
 * @modified By：
 *  全局异常
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ExceptionCodeEnums enums) {
        super(enums.getMsg());
        this.code = enums.getCode();
    }

    public SellException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

}

package com.toni.sell.utils;

import com.toni.sell.enums.CodeEnum;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/18 0018 15:04
 * @modified By：
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){

        for (T t : enumClass.getEnumConstants()) {
            if(code.equals(t.getCode())){
                return t;
            }
        }
        return null;
    }

}

package com.toni.sell.utils;

import java.util.Random;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 10:39
 * @modified By：
 *
 *  生成唯一主键
 */
public class KeyUtils {

    public static synchronized String gen() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}

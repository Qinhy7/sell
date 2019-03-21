package com.toni.sell.utils;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/18 0018 10:14
 * @modified By：
 */
public class MathUtils {

    private static final Double MONEY_RANGE = 0.001;

    private MathUtils(){}

    public static Boolean isEqual(Double num1 , Double num2) {
        return Math.abs(num1 - num2) < MONEY_RANGE;
    }

}

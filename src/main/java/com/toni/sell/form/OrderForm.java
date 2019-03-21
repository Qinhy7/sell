package com.toni.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 19:24
 * @modified By：
 *  订单前台bean
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "电话不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "openid不能为空")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;

}

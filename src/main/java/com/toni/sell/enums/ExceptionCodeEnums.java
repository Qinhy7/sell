package com.toni.sell.enums;

import lombok.Getter;

/**
 * 异常状态码
 */
@Getter
public enum ExceptionCodeEnums {

    PATAM_ERROR("参数错误",1),
    ORDER_CART_EMPTY("购物车位空",2),
    PRODUCT_NOT_FOUND( "未找到商品", 10),
    PRODUCT_STOCK_ERROR("库存不足",11),
    PRODUCT_STATUS_ERROR("商品状态不对",23),
    ORDER_NOT_FOUND("没有这个订单",12),
    ORDER_DETAIL_NOT_FOUND("没有订单详情",13),
    ORDER_STATUS_ERROR("订单状态不正确",14),
    ORDER_STATUS_UPDATE_FAIL("修改订单状态错误",15),
    ORDER_PAY_STATUS_ERROR("支付状态错误",16),
    ORDER_PAY_UPDATE_FAIL("支付状态修改异常",17),
    ORDER_OPENID_ERROR("openid和orderId不对应",18),
    WECHAT_MP_ERROR("微信网页授权失败",19),
    PAY_MONEY_NOT_EQUAL("微信异步通知校验金额出错",20),
    ORDER_CANCEL_SUCCESS("订单取消成功",21),
    ORDER_FINISH_SUCCESS("订单完结成功",22),
    LOGIN_FAIL("不存在该用户",23),
    LOGOUT_SUCCESS("退出成功",24)
    ;

    private String msg;
    private Integer code;

    ExceptionCodeEnums(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }
}

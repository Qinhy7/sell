package com.toni.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.toni.sell.bean.OrderDetail;
import com.toni.sell.enums.OrderStatusEnums;
import com.toni.sell.enums.PayStatusEnums;
import com.toni.sell.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 10:05
 * @modified By：
 *
 *  在OrderMaster 的基础上新增了List<OrderDetail> 用来记录订单详情
 */
@Data
public class OrderMasterDTO {

    private String orderId;

    /**买家姓名*/
    private String buyerName;

    /**买家手机号码*/
    private String buyerPhone;

    /**买家送货地址*/
    private String buyerAddress;

    /**买家微信openid*/
    private String buyerOpenid;

    /**订单总额*/
    private BigDecimal orderAmount;

    /**订单状态*/
    private Integer orderStatus = OrderStatusEnums.NEW.getCode();

    /**支付状态*/
    private Integer payStatus = PayStatusEnums.NEW.getCode();

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /** 订单详情*/
    private List<OrderDetail> details;

    @JsonIgnore
    public OrderStatusEnums getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnums.class);
    }

    @JsonIgnore
    public PayStatusEnums getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnums.class);
    }

}

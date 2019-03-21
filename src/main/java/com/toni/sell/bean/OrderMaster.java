package com.toni.sell.bean;

import com.toni.sell.enums.OrderStatusEnums;
import com.toni.sell.enums.PayStatusEnums;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 9:42
 * @modified By：
 */
@Data
@Entity
public class OrderMaster {

    @Id
    /**订单id*/
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

}

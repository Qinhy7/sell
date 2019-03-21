package com.toni.sell.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 9:48
 * @modified By：
 */
@Data
@Entity
public class OrderDetail {

    @Id
    /**订单详情id*/
    private String detailId;

    /**订单id*/
    private String orderId;

    /**商品id*/
    private String productId;

    /**商品名称*/
    private String productName;

    /**商品单价*/
    private BigDecimal productPrice;

    /**商品数量*/
    private Integer productQuantity;

    /**商品图标*/
    private String productIcon;

}

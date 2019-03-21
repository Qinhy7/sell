package com.toni.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/19 0019 10:06
 * @modified By：
 */
@Data
public class ProductForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /* 库存 */
    private Integer productStock;

    /* 商品描述 */
    private  String productDescription;

    /* 商品图标 */
    private String productIcon;

    /* 商品状态,0正常1下架 */
    private Integer productStatus = 0;

    /* 商品分类 */
    private Integer categoryType;

}

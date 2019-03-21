package com.toni.sell.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toni.sell.enums.ProductInfoEnum;
import com.toni.sell.utils.EnumUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/15 0015 15:22
 * @modified By：
 */
@Entity
@Data
@NoArgsConstructor
public class ProductInfo {

    @Id
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
    private Integer productStatus;

    /* 商品分类 */
    private Integer categoryType;

    /* 创建时间 */
    private Date createTime;

    /* 更新时间 */
    private Date updateTime;

    @JsonIgnore
    public ProductInfoEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductInfoEnum.class);
    }

}

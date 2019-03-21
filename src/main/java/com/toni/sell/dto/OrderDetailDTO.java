package com.toni.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 10:16
 * @modified By：
 *  在个层传递的orderdetail对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    /* 商品id */
    private String productId;

    /* 商品数量 */
    private Integer productQuantity;
}

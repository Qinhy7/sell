package com.toni.sell.service;

import com.toni.sell.bean.ProductInfo;
import com.toni.sell.dto.OrderDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    /* 根据分页信息获取商品信息 */
    Page<ProductInfo> getAll(Pageable page);

    /* 获取上架商品的信息 */
    List<ProductInfo> getByUp(Integer status);

    ProductInfo getOne(String categoryInfoId);

    ProductInfo save(ProductInfo productInfo);

    ProductInfo onSale(String productInfoId);

    ProductInfo offSale(String productInfoId);


    // 加库存
    void increase(List<OrderDetailDTO> list);
    // 减库存
    void decrease(List<OrderDetailDTO> list);

}

package com.toni.sell.repository.mapper;

import com.toni.sell.bean.ProductInfo;
import org.apache.ibatis.annotations.Insert;

public interface SellerProductMapper {

    @Insert("insert into product_info(product_id,product_name,product_price,product_stock,category_type) " +
            "values(#{productId,jdbcType=VARCHAR},#{productName,jdbcType=VARCHAR},#{productPrice,jdbcType=DECIMAL},#{productStock,jdbcType=INTEGER},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductInfo productInfo);

    int updateByObject(ProductInfo productInfo);
}

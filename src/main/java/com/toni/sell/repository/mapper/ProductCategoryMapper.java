package com.toni.sell.repository.mapper;

import com.toni.sell.bean.ProductCategory;
import org.apache.ibatis.annotations.Insert;


public interface ProductCategoryMapper {

    @Insert("insert into product_category (category_type , category_name) values (#{categoryType , jdbcType=INTEGER} , #{categoryName , jdbcType=VARCHAR})")
    int insertByObject(ProductCategory productCategory);

}

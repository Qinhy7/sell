package com.toni.sell.service;

import com.toni.sell.bean.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory getOne(Integer categoryId);

    List<ProductCategory> getAll();

    List<ProductCategory> getByCategoryIn(List<Integer> list);

    ProductCategory save(ProductCategory category);

}

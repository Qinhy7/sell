package com.toni.sell.service.impl;

import com.toni.sell.bean.ProductCategory;
import com.toni.sell.repository.ProductCategoryRepository;
import com.toni.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/15 0015 14:37
 * @modified By：
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory getOne(Integer categoryId) {
        return productCategoryRepository.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> getByCategoryIn(List<Integer> list) {
        return productCategoryRepository.findByCategoryTypeIn(list);
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return productCategoryRepository.save(category);
    }
}

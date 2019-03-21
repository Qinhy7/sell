package com.toni.sell.service.impl;

import com.toni.sell.bean.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl service;

    @Test
    public void getOne() {
        System.out.println(service.getOne(1));
    }

    @Test
    public void getAll() {
        System.out.println(service.getAll());
    }

    @Test
    public void getByCategoryIn() {
    }

    @Test
    public void save() {
        ProductCategory category = new ProductCategory();
        category.setCategoryId(2);
        category.setCategoryName("女生区");
        category.setCategoryType(6);

        ProductCategory category1 = service.save(category);
        Assert.assertNotNull(category1);
    }
}
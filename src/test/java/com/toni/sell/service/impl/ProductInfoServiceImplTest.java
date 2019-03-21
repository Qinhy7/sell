package com.toni.sell.service.impl;

import com.toni.sell.bean.ProductInfo;
import com.toni.sell.enums.ProductInfoEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl impl;

    @Test
    public void getAll() {
    }

    @Test
    public void getByUp() {
    }

    @Test
    public void getOne() {
    }

    @Test
    public void save() {
        ProductInfo info = new ProductInfo();
        info.setCategoryType(2);
        info.setProductDescription("真香！");
        info.setProductIcon("http://www.xxxxxxx.com");
        info.setProductId("1234543");
        info.setProductName("炒饼丝");
        info.setProductPrice(new BigDecimal(9));
        info.setProductStatus(ProductInfoEnum.UP.getCode());
        info.setProductStock(231);

        impl.save(info);
    }
}
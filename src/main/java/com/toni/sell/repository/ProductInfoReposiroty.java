package com.toni.sell.repository;

import com.toni.sell.bean.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoReposiroty extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findAllByProductStatus(Integer integer);

}

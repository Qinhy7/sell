package com.toni.sell.repository;

import com.toni.sell.bean.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/15 0015 14:30
 * @modified By：
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 获取包含指定商品类型的信息
     * @param list
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

}

package com.toni.sell.service.impl;

import com.toni.sell.bean.ProductInfo;
import com.toni.sell.dto.OrderDetailDTO;
import com.toni.sell.enums.ExceptionCodeEnums;
import com.toni.sell.enums.ProductInfoEnum;
import com.toni.sell.exception.SellException;
import com.toni.sell.repository.ProductInfoReposiroty;
import com.toni.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/15 0015 15:35
 * @modified By：
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoReposiroty reposiroty;

    @Override
    public Page<ProductInfo> getAll(Pageable page) {
        return reposiroty.findAll(page);
    }

    @Override
    public List<ProductInfo> getByUp(Integer status) {
        return reposiroty.findAllByProductStatus(status);
    }

    @Override
    public ProductInfo getOne(String categoryInfoId) {
        return reposiroty.getOne(categoryInfoId);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return reposiroty.save(productInfo);
    }

    @Override
    public ProductInfo onSale(String productInfoId) {
        ProductInfo productInfo = reposiroty.getOne(productInfoId);
        if (productInfo == null) {
            throw new SellException(ExceptionCodeEnums.PRODUCT_NOT_FOUND);
        }
        if (productInfo.getProductStatusEnum() == ProductInfoEnum.UP) {
            throw new SellException(ExceptionCodeEnums.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductInfoEnum.UP.getCode());
        return reposiroty.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productInfoId) {
        ProductInfo productInfo = reposiroty.getOne(productInfoId);
        if (productInfo == null) {
            throw new SellException(ExceptionCodeEnums.PRODUCT_NOT_FOUND);
        }
        if (productInfo.getProductStatusEnum() == ProductInfoEnum.DOWN) {
            throw new SellException(ExceptionCodeEnums.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductInfoEnum.DOWN.getCode());
        return reposiroty.save(productInfo);
    }

    @Override
    public void increase(List<OrderDetailDTO> list) {
        for (OrderDetailDTO orderDetailDTO : list) {
            ProductInfo productInfo = reposiroty.getOne(orderDetailDTO.getProductId());
            if(null == productInfo){ // 不存在
                throw new SellException(ExceptionCodeEnums.PRODUCT_NOT_FOUND);
            }

            Integer sum = productInfo.getProductStock() + orderDetailDTO.getProductQuantity();
            productInfo.setProductStock(sum);

            reposiroty.save(productInfo);
        }
    }

    @Override
    public void decrease(List<OrderDetailDTO> list) {
        for (OrderDetailDTO detailDTO : list) {
            ProductInfo productInfo = reposiroty.getOne(detailDTO.getProductId());
            if(null == productInfo){ // 不存在
                throw new SellException(ExceptionCodeEnums.PRODUCT_NOT_FOUND);
            }

            // 检查库存
            int res = productInfo.getProductStock() - detailDTO.getProductQuantity();
            if(res < 0){
                throw  new SellException(ExceptionCodeEnums.PRODUCT_STOCK_ERROR);
            }

            // 修改库存
            productInfo.setProductStock(res);
            reposiroty.save(productInfo);
        }
    }
}

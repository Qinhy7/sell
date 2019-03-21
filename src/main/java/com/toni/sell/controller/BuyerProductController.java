package com.toni.sell.controller;

import com.toni.sell.VO.ProductInfoVO;
import com.toni.sell.VO.ProductVO;
import com.toni.sell.VO.ResultVO;
import com.toni.sell.bean.ProductCategory;
import com.toni.sell.bean.ProductInfo;
import com.toni.sell.enums.ProductInfoEnum;
import com.toni.sell.service.ProductCategoryService;
import com.toni.sell.service.ProductInfoService;
import com.toni.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/15 0015 18:50
 * @modified By：
 * 买家端controller
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO getList(){
        // 查询上架商品
        List<ProductInfo> productInfos = productInfoService.getByUp(ProductInfoEnum.UP.getCode());

        // 查询商品分类
        List<Integer> integers = productInfos.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategories = productCategoryService.getByCategoryIn(integers);

        // 拼装结果
        ArrayList<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategory category : productCategories) {
            ProductVO<ProductInfoVO> productVO = new ProductVO<>();
            BeanUtils.copyProperties(category,productVO);

            ArrayList<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo info : productInfos) {
                if(info.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO infoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(info, infoVO);

                    productInfoVOS.add(infoVO);
                }
            }
            productVO.setCategaryInfoVOList(productInfoVOS);
            productVOS.add(productVO);
        }

        // 返回值
        return ResultVOUtils.success(productVOS);
    }

}

package com.toni.sell.controller;

import com.toni.sell.VO.ResultVO;
import com.toni.sell.convert.OrderForm2OrderMasterDTO;
import com.toni.sell.dto.OrderMasterDTO;
import com.toni.sell.enums.ExceptionCodeEnums;
import com.toni.sell.exception.SellException;
import com.toni.sell.form.OrderForm;
import com.toni.sell.service.OrderService;
import com.toni.sell.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 19:21
 * @modified By：
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        // 判断是否有错误信息
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数错误：orderForm : ",orderForm);
            throw new SellException(ExceptionCodeEnums.PATAM_ERROR.getCode(),
                    bindingResult.getFieldError().getField());
        }

        // 转换格式
        OrderMasterDTO orderMasterDTO = OrderForm2OrderMasterDTO.convert(orderForm);
        if(CollectionUtils.isEmpty(orderMasterDTO.getDetails())){
            log.error("【创建订单】购物车不能为空，orderDTO={}" , orderMasterDTO);
            throw new SellException(ExceptionCodeEnums.ORDER_CART_EMPTY);
        }
        // 创建订单
        OrderMasterDTO dto = orderService.create(orderMasterDTO);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderId",dto.getOrderId());

        return ResultVOUtils.success(hashMap);
    }


    // 订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderMasterDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page" , defaultValue = "0") Integer page,
                                         @RequestParam(value = "size" , defaultValue = "10") Integer size) {
        // 判断openid
        if(StringUtils.isEmpty(openid)){
            log.error("【订单列表】 opendi参数错误，openid ：",openid);
            throw new SellException(ExceptionCodeEnums.PATAM_ERROR);
        }

        PageRequest of = PageRequest.of(page, size);
        List<OrderMasterDTO> all = orderService.getAll(openid, of);

        return ResultVOUtils.success(all);
    }

    // 订单详情
    @GetMapping("/detail")
    public ResultVO<OrderMasterDTO> detail(@PathVariable("openId") String openid,
                                           @PathVariable("orderId") String orderId){

        OrderMasterDTO orderMasterDTO = orderService.getOne(orderId);
        if(!openid.equalsIgnoreCase(orderMasterDTO.getBuyerOpenid())){
            // openid 不对应
            log.error("【查看订单】openId和orderId不对应，opendid={}, orderMasterDTO={}",openid,orderMasterDTO);
            throw new SellException(ExceptionCodeEnums.ORDER_OPENID_ERROR);
        }

        return ResultVOUtils.success(orderMasterDTO);
    }

    // 取消订单
    @PostMapping("/cancle")
    public ResultVO cancle(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){

        OrderMasterDTO one = orderService.getOne(orderId);
        if(null == one){
            log.error("【取消订单】订单不存在，opendid={}, orderMasterDTO={}",openid,one);
            throw new SellException(ExceptionCodeEnums.ORDER_NOT_FOUND);
        }
        orderService.cancel(one);

        return ResultVOUtils.success();
    }

}

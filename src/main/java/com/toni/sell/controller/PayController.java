package com.toni.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.toni.sell.dto.OrderMasterDTO;
import com.toni.sell.enums.ExceptionCodeEnums;
import com.toni.sell.exception.SellException;
import com.toni.sell.service.OrderService;
import com.toni.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/18 0018 8:40
 * @modified By：
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId
            , @RequestParam("returnUrl") String returnUrl , Map<String , Object> map) {
        //1查找订单
        OrderMasterDTO orderDTO = orderService.getOne(orderId);
        if(orderDTO == null) {
            log.error("【微信下单】订单不存在，orderId={}" , orderId);
            throw new SellException(ExceptionCodeEnums.ORDER_NOT_FOUND);
        }

        //2发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse" , payResponse);
        map.put("returnUrl" , returnUrl);

        return new ModelAndView("pay/create" , map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        //返回给微信处理结果，让微信不再进行异步通知
        return new ModelAndView("pay/success");
    }

}

package com.toni.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.toni.sell.bean.OrderMaster;
import com.toni.sell.dto.OrderMasterDTO;
import com.toni.sell.enums.ExceptionCodeEnums;
import com.toni.sell.exception.SellException;
import com.toni.sell.service.OrderService;
import com.toni.sell.service.PayService;
import com.toni.sell.utils.JsonUtils;
import com.toni.sell.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/18 0018 8:45
 * @modified By：
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private final static String ORDER_NAME = "微信点餐支付";

    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderMasterDTO orderMasterDTO) {
        //1.设置payRequest
        PayRequest payRequest = new PayRequest();
        //2.设置订单信息
        payRequest.setOpenid(orderMasterDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderMasterDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderMasterDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【发起支付】payRequest={}" , JsonUtils.toJson(payRequest));
        //3.预支付
        PayResponse payResponse =  bestPayService.pay(payRequest);

        log.info("【发起支付】payResponse={}" , JsonUtils.toJson(payResponse));
        //4.进行支付
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.处理异步通知
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【异步通知】payResponse={}" , JsonUtils.toJson(payResponse));

        //2.获取订单
        OrderMasterDTO orderDTO = orderService.getOne(payResponse.getOrderId());
        if(null == orderDTO) {
            log.error("【异步通知】订单不存在，orderId={}" , payResponse.getOrderId());
            throw new SellException(ExceptionCodeEnums.ORDER_NOT_FOUND);
        }

        //3.判断订单有效性
        //3.1判断签名 best-sdk 已经处理好
        //3.2判断支付状态 best-sdk 已经处理好
        //3.3判断支付金额

        if(!MathUtils.isEqual(orderDTO.getOrderAmount().doubleValue() , payResponse.getOrderAmount())) {
            //支付金额不一致
            log.error("【异步通知】支付金额与原订单金额不一致，微信请求金额={} , 订单原今额={}，orderId={}"
                    , payResponse.getOrderAmount() , orderDTO.getOrderAmount() , payResponse.getOrderId());
            throw new SellException(ExceptionCodeEnums.PAY_MONEY_NOT_EQUAL);
        }

        //3.4判断支付人（下单人==支付人）

        //3.更改订单支付状态
        orderService.pay(orderDTO);

        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderMasterDTO orderMasterDTO) {

        //1.配置退款参数
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderMasterDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderMasterDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】refundRequest={}" , JsonUtils.toJson(refundRequest));

        //2.执行微信退款
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】refundResponse={}" , JsonUtils.toJson(refundResponse));

        return refundResponse;
    }
}

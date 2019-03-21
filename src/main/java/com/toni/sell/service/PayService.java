package com.toni.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.toni.sell.bean.OrderMaster;
import com.toni.sell.dto.OrderMasterDTO;

public interface PayService {

    /**
     * 下单
     * @param orderMasterDTO
     * @return
     */
    PayResponse create(OrderMasterDTO orderMasterDTO);


    /**
     * 处理异步通知
     * @param notifyData
     */
    PayResponse notify(String notifyData);


    /**
     * 退款
     * @param orderMasterDTO
     * @return
     */
    RefundResponse refund(OrderMasterDTO orderMasterDTO);

}

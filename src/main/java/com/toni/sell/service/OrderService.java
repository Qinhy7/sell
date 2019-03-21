package com.toni.sell.service;

import com.toni.sell.dto.OrderMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    /**
     * 支付订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO pay(OrderMasterDTO orderMasterDTO);

    /**
     * 结束订单
     * @param orderMasterDTO
     */
    OrderMasterDTO finash(OrderMasterDTO orderMasterDTO);

    /**
     * 取消订单
     * @param orderMasterDTO
     */
    OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO);

    /**
     * 创建订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO);

    /**
     * 查询指定订单
     * @param orderId
     * @return
     */
    OrderMasterDTO getOne(String orderId);

    /**
     * 获取所有订单信息
     * @param pageable
     * @return
     */
    Page<OrderMasterDTO> getAll(Pageable pageable);

    /**
     * 获取用户订单信息
     * @param openId
     * @param pageable
     * @return
     */
    List<OrderMasterDTO> getAll(String openId, Pageable pageable);

}

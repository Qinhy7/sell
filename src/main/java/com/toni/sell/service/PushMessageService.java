package com.toni.sell.service;

import com.toni.sell.dto.OrderMasterDTO;

/**
 * 推送消息模板
 *
 * @author ：qinhy
 * @date ：Created in 2019/3/20 0020 14:24
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderMasterDTO
     */
    void orderStatus(OrderMasterDTO orderMasterDTO);
}

package com.toni.sell.convert;

import com.toni.sell.bean.OrderMaster;
import com.toni.sell.dto.OrderMasterDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 15:22
 * @modified By：
 * 转换工具类
 */
public class OrderMaster2OrderMasterDTO {

    public static OrderMasterDTO convert(OrderMaster orderMaster){
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        return orderMasterDTO;
    }

    public static List<OrderMasterDTO> convert(List<OrderMaster> orderMasters){
        return orderMasters.stream().map(e -> convert(e))
                .collect(Collectors.toList());
    }

}

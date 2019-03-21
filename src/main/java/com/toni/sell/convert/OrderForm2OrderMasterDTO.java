package com.toni.sell.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.toni.sell.bean.OrderDetail;
import com.toni.sell.dto.OrderMasterDTO;
import com.toni.sell.enums.ExceptionCodeEnums;
import com.toni.sell.exception.SellException;
import com.toni.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 19:33
 * @modified By：
 */
@Slf4j
public class OrderForm2OrderMasterDTO {

    public static OrderMasterDTO convert(OrderForm orderForm){

        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setBuyerName(orderForm.getName());
        orderMasterDTO.setBuyerPhone(orderForm.getPhone());
        orderMasterDTO.setBuyerAddress(orderForm.getAddress());
        orderMasterDTO.setBuyerOpenid(orderForm.getOpenid());

        ArrayList<OrderDetail> objects = new ArrayList<>();
        Gson gson = new Gson();
        try{
            objects = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch(Exception e){
            log.error("【对象装换】错误，string={}" , orderForm.getItems());
            throw new SellException(ExceptionCodeEnums.PATAM_ERROR);
        }

        orderMasterDTO.setDetails(objects);

        return orderMasterDTO;
    }

}

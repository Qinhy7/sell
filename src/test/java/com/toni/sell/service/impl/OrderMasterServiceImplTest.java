package com.toni.sell.service.impl;

import com.toni.sell.bean.OrderDetail;
import com.toni.sell.dto.OrderMasterDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderServiceImpl orderMasterService;

    @Test
    public void create() {

        for (int i = 0; i < 34; i++) {

            OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
            orderMasterDTO.setBuyerAddress("南阳理工学校");
            orderMasterDTO.setBuyerName("吕布-"+i);
            orderMasterDTO.setBuyerOpenid(UUID.randomUUID().toString().substring(0,10));
            orderMasterDTO.setBuyerPhone("1132432433"+i);

            List<OrderDetail> details = new ArrayList<>();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId("123333");
            orderDetail.setProductQuantity(i%3+1);
            details.add(orderDetail);

            OrderDetail orderDetail1 = new OrderDetail();
            orderDetail1.setProductId("332324");
            orderDetail1.setProductQuantity(i%3+1);
            details.add(orderDetail1);

            orderMasterDTO.setDetails(details);

            OrderMasterDTO masterDTO = orderMasterService.create(orderMasterDTO);
        }

//        System.out.println(masterDTO);
    }

    @Test
    public void getOne(){
        OrderMasterDTO orderMasterDTO = orderMasterService.getOne("1552720365895484291");
        System.out.println(orderMasterDTO);
    }

    @Test
    public void getAll(){
        List<OrderMasterDTO> all = orderMasterService.getAll("2234543", PageRequest.of(0, 1));
        System.out.println(all);
    }

    @Test
    public void getAll1(){
        System.out.println(orderMasterService.getAll(PageRequest.of(0, 2)));
    }

    @Test
    public void cancel(){
        OrderMasterDTO one = orderMasterService.getOne("1552718139646902339");

        OrderMasterDTO cancel = orderMasterService.cancel(one);
        System.out.println(cancel);
    }

    @Test
    public void finash(){
        OrderMasterDTO one = orderMasterService.getOne("1552718139646902339");

        OrderMasterDTO cancel = orderMasterService.finash(one);
        System.out.println(cancel);
    }

    @Test
    public void pay(){
        OrderMasterDTO one = orderMasterService.getOne("1552718139646902339");
        OrderMasterDTO cancel = orderMasterService.pay(one);
        System.out.println(cancel);
    }

}
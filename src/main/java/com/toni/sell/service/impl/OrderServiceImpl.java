package com.toni.sell.service.impl;

import com.toni.sell.bean.OrderDetail;
import com.toni.sell.bean.OrderMaster;
import com.toni.sell.bean.ProductInfo;
import com.toni.sell.convert.OrderMaster2OrderMasterDTO;
import com.toni.sell.dto.OrderDetailDTO;
import com.toni.sell.dto.OrderMasterDTO;
import com.toni.sell.enums.ExceptionCodeEnums;
import com.toni.sell.enums.OrderStatusEnums;
import com.toni.sell.enums.PayStatusEnums;
import com.toni.sell.exception.SellException;
import com.toni.sell.repository.OrderDetailRepository;
import com.toni.sell.repository.OrderMasterRepository;
import com.toni.sell.repository.ProductInfoReposiroty;
import com.toni.sell.service.OrderService;
import com.toni.sell.service.PayService;
import com.toni.sell.service.ProductInfoService;
import com.toni.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/16 0016 10:11
 * @modified By：
 */
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoReposiroty productInfoReposiroty;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private PayService payService;


    @Override
    public OrderMasterDTO pay(OrderMasterDTO orderMasterDTO) {
        // 判断订单状态，只有是新订单才能修改支付状态
        if(!OrderStatusEnums.NEW.getCode().equals(orderMasterDTO.getOrderStatus())){
            // 不是新下单的状态，抛出异常
            log.error(" 【支付订单】 订单状态不正确：orderId={}, orderStatus={}",orderMasterDTO.getOrderId(),orderMasterDTO.getOrderStatus());
            throw new SellException(ExceptionCodeEnums.ORDER_STATUS_ERROR);
        }
        // 判断支付状态
        if(!PayStatusEnums.NEW.getCode().equals(orderMasterDTO.getPayStatus())){
            // 不是未支付状态，抛出异常
            log.error(" 【支付订单】 支付状态不正确：orderId={}, payStatus={}",orderMasterDTO.getOrderId(),orderMasterDTO.getPayStatus());
            throw new SellException(ExceptionCodeEnums.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO,orderMaster);
        orderMaster.setPayStatus(PayStatusEnums.FINISH.getCode());

        OrderMaster save = orderMasterRepository.save(orderMaster);
        if(null == save){
            log.error(" 【支付订单】 支付状态修改异常：orderIMaster",orderMaster);
            throw new SellException(ExceptionCodeEnums.ORDER_PAY_UPDATE_FAIL);
        }
        return OrderMaster2OrderMasterDTO.convert(save);
    }

    @Override
    public OrderMasterDTO finash(OrderMasterDTO orderMasterDTO) {
        // 判断订单状态
        if(!OrderStatusEnums.NEW.getCode().equals(orderMasterDTO.getOrderStatus())){
            // 不是新下单的状态，抛出异常
            log.error(" 【完结订单】 订单状态不正确：orderId={}, orderStatus={}",orderMasterDTO.getOrderId(),orderMasterDTO.getOrderStatus());
            throw new SellException(ExceptionCodeEnums.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnums.FINISH.getCode());
        OrderMaster save = orderMasterRepository.save(orderMaster);
        if(null == save){
            log.error(" 【完结订单】 更新订单状态错误： orderMasterDTO={}",orderMasterDTO);
            throw new SellException(ExceptionCodeEnums.ORDER_STATUS_UPDATE_FAIL);
        }

        return OrderMaster2OrderMasterDTO.convert(save);
    }

    @Override
    public OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) {
        // 判断订单状态
        OrderMaster orderMaster = orderMasterRepository.getOne(orderMasterDTO.getOrderId());
        if(!OrderStatusEnums.NEW.getCode().equals(orderMaster.getOrderStatus())){
            // 不是新下单的状态，抛出异常
            log.error(" 【取消订单】 订单状态不正确：orderId={}, orderStatus={}",orderMaster.getOrderId(),orderMaster.getOrderStatus());
            throw new SellException(ExceptionCodeEnums.ORDER_STATUS_ERROR);
        }

        // 修改状态
        orderMaster.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
        OrderMaster save = orderMasterRepository.save(orderMaster);
        if(null == save){
            log.error(" 【取消订单】 更新订单状态错误： orderMasterDTO={}",orderMasterDTO);
            throw new SellException(ExceptionCodeEnums.ORDER_STATUS_UPDATE_FAIL);
        }

        // 修改库存、
        if(CollectionUtils.isEmpty(orderMasterDTO.getDetails())){
            log.error(" 【取消订单】订单中无商品详情：orderMasterDTO={}",orderMasterDTO);
            throw new SellException(ExceptionCodeEnums.ORDER_DETAIL_NOT_FOUND);
        }
        List<OrderDetailDTO> collect = orderMasterDTO.getDetails().stream()
                .map(e -> new OrderDetailDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increase(collect);

        // 如果已经支付，需要支付
        if(PayStatusEnums.FINISH.getCode().equals(orderMasterDTO.getPayStatus())){
//            payService.refund(orderMasterDTO);
        }
        return orderMasterDTO;
    }

    @Override
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {
        BigDecimal total = new BigDecimal(0);
        // 生成order_id
        String orderId = KeyUtils.gen();

        // 查询订购的商品，计算总计
        for (OrderDetail detail : orderMasterDTO.getDetails()) {
            // 对比数据库，查看商品是否存在
            ProductInfo productInfo = productInfoReposiroty.getOne(detail.getProductId());
            if(StringUtils.isEmpty(productInfo)){ // 商品不存在
                throw new SellException(ExceptionCodeEnums.PRODUCT_NOT_FOUND);
            }

            // 计算商品总和
            total = total.add(productInfo.getProductPrice()
                    .multiply(new BigDecimal(detail.getProductQuantity())));

            // 补全商品详情信息, detail_id、product_name、price、icon、order_id
            BeanUtils.copyProperties(productInfo, detail);
            detail.setDetailId(KeyUtils.gen());
            detail.setOrderId(orderId);

            // 详情表入库
            orderDetailRepository.save(detail);
        }

        // 数据入库
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderMasterDTO , orderMaster); // 会把源数据的null也拷贝到目的数据中
        orderMaster.setOrderAmount(total);
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.NEW.getCode());
        orderMasterRepository.save(orderMaster);

        // 减商品库存
        List<OrderDetailDTO> collect = orderMasterDTO.getDetails().stream()
                .map(e -> new OrderDetailDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decrease(collect);

        return orderMasterDTO;
    }

    @Override
    public OrderMasterDTO getOne(String orderId) {
        // 查询主订单
        OrderMaster master = orderMasterRepository.getOne(orderId);
        if(null == master ){
            throw new SellException(ExceptionCodeEnums.ORDER_NOT_FOUND);
        }

        // 查询订单详情表
        List<OrderDetail> details = orderDetailRepository.findByOrderId(master.getOrderId());
        if(CollectionUtils.isEmpty(details)){
            throw new SellException(ExceptionCodeEnums.ORDER_DETAIL_NOT_FOUND);
        }

        OrderMasterDTO masterDTO = OrderMaster2OrderMasterDTO.convert(master);
        masterDTO.setDetails(details);

        return masterDTO;
    }

    @Override
    public Page<OrderMasterDTO> getAll(Pageable pageable) {
        Page<OrderMaster> all = orderMasterRepository.getAllBy(pageable);
        List<OrderMasterDTO> convert = OrderMaster2OrderMasterDTO.convert(all.getContent());

        return new PageImpl<>(convert, pageable, all.getTotalElements());
    }

    @Override
    public List<OrderMasterDTO> getAll(String openId, Pageable pageable) {
        // 查询用户订单
        List<OrderMaster> orderMasters = orderMasterRepository.getByBuyerOpenid(openId, pageable);
        return OrderMaster2OrderMasterDTO.convert(orderMasters);
    }


}

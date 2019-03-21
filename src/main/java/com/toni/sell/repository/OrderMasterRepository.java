package com.toni.sell.repository;

import com.toni.sell.bean.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    List<OrderMaster> getByBuyerOpenid(String openid, Pageable pageable);

    Page<OrderMaster> getAllBy(Pageable pageable);

}

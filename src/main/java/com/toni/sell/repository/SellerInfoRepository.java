package com.toni.sell.repository;

import com.toni.sell.bean.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/20 0020 8:48
 * @modified By：
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}

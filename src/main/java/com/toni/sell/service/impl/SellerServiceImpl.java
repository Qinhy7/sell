package com.toni.sell.service.impl;

import com.toni.sell.bean.SellerInfo;
import com.toni.sell.repository.SellerInfoRepository;
import com.toni.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/20 0020 8:49
 * @modified By：
 */
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }

}

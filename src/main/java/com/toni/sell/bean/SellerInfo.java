package com.toni.sell.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/20 0020 8:47
 * @modified By：
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

}

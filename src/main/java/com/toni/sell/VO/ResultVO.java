package com.toni.sell.VO;

import lombok.Data;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/15 0015 18:53
 * @modified By：
 * <p>
 * 返回给前端的数据
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

}

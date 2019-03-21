package com.toni.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/15 0015 18:55
 * @modified By：
 */
@Data
public class ProductVO<T> {
    // 返回给前端的名字
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<T> categaryInfoVOList;

}

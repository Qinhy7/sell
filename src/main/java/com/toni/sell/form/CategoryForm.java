package com.toni.sell.form;

import lombok.Data;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/19 0019 15:51
 * @modified By：
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

}

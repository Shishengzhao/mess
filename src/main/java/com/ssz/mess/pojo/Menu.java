package com.ssz.mess.pojo;

import lombok.Data;

@Data
public class Menu {

    private Integer id;

    /**
     * 菜号
     */
    private Integer menuId;

    /**
     * 菜名
     */
    private String name;

    /**
     * 菜价格
     */
    private Integer price;

    /**
     * 店铺id
     */
    private Integer storeId;

}
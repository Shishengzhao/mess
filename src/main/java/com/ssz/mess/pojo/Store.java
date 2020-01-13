package com.ssz.mess.pojo;

import lombok.Data;

@Data
public class Store {

    /**
     * 店铺账号
     */
    private Integer id;

    private String password;

    /**
     * 店铺窗口号
     */
    private Integer storeId;

    /**
     * 店名
     */
    private String name;

    /**
     * 所在楼层
     */
    private Integer floor;

    private Integer phone;

    /**
     * 角色 默认为 2(店家)
     */
    private Integer role;

    /**
     * 评分id
     */
    private Integer gradeId;

}
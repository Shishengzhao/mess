package com.ssz.mess.pojo;

import lombok.Data;

@Data
public class Grade {

    /**
     * 评分编号
     */
    private Integer id;

    /**
     * 总分
     */
    private Integer totalpoints;

    /**
     * 评分次数
     */
    private Integer time;


}
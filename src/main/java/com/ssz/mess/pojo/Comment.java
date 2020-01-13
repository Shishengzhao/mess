package com.ssz.mess.pojo;

import lombok.Data;

@Data
public class Comment {

    private Integer id;

    private String name;

    private String content;

    private Integer storeId;

}
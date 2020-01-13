package com.ssz.mess.pojo.vo;

import com.ssz.mess.pojo.Comment;
import com.ssz.mess.pojo.Grade;
import com.ssz.mess.pojo.Menu;
import com.ssz.mess.pojo.Store;
import lombok.Data;

import java.util.List;

@Data
public class StoreVo {

    private Store store;

    /**
     * 店家与评分 一对一关系
     */
    private Grade grade;

    /**
     * 店家与菜单 一对多关系
     */
    private List<Menu> menus;

    /**
     * 店家与评论 一对多关系
     */
    private List<Comment> comments;
}

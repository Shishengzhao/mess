package com.ssz.mess.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.ssz.mess.pojo.Menu;
import com.ssz.mess.pojo.MenuExample;

public interface MenuMapper {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Menu getMenuById(Integer id);

    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}
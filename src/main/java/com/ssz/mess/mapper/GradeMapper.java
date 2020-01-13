package com.ssz.mess.mapper;

import java.util.List;

import com.ssz.mess.pojo.Grade;
import org.apache.ibatis.annotations.Param;
import com.ssz.mess.pojo.GradeExample;

public interface GradeMapper {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Grade getGradeById(Integer id);

    long countByExample(GradeExample example);

    int deleteByExample(GradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    int insertSelective(Grade record);

    List<Grade> selectByExample(GradeExample example);

    Grade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Grade record, @Param("example") GradeExample example);

    int updateByExample(@Param("record") Grade record, @Param("example") GradeExample example);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
}
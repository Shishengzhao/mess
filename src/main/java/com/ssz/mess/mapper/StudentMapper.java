package com.ssz.mess.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ssz.mess.pojo.Student;
import com.ssz.mess.pojo.StudentExample;

public interface StudentMapper {

    Student getStudentById(Integer id);

    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);
}
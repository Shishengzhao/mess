package com.ssz.mess.mapper;

import com.ssz.mess.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Insert("insert into user values(#{id},#{password},#{role})")
    int insertUser(User user);

    @Select("select * from user where id = #{id}")
    User getUserById(Integer id);

    /**
     * 根据账号和密码查询User表
     * @param id
     * @param password
     * @return
     */
    @Select("select * from user where id = #{id} and password = #{password}")
    User getUserWithIdAndPassword(Integer id,String password);

    @Select("select * from user")
    List<User> getUsers();

    @Update("update user set password=#{password},role=#{role} where id = #{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(Integer id);
}

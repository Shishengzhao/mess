package com.ssz.mess;

import com.ssz.mess.mapper.UserMapper;
import com.ssz.mess.pojo.User;
import com.ssz.mess.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testUser(){

        List<User> users = userMapper.getUsers();
        for (User user : users) {
            System.out.println(user);
        }

    }
    @Test
    void t(){

        String pwd = "123456";
        String s = MD5Utils.code(pwd);
        System.out.println(s);
        System.out.println(MD5Utils.code(s));
    }
}

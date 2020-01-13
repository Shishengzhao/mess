package com.ssz.mess.service;

import com.ssz.mess.pojo.User;

import java.util.List;

public interface IUserService {

    /**
     * 验证用户的账号和密码
     * @param id
     * @param password
     * @return
     */
    User checkUser(Integer id, String password);

    boolean saveUser(User user);

    User getUserById(Integer id);

    List<User> getUsers();

    boolean updateUser(Integer id, User user);

    void deleteUser(Integer id);

}

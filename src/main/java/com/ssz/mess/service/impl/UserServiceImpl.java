package com.ssz.mess.service.impl;

import com.ssz.mess.mapper.UserMapper;
import com.ssz.mess.pojo.Store;
import com.ssz.mess.pojo.Student;
import com.ssz.mess.pojo.User;
import com.ssz.mess.service.IStoreService;
import com.ssz.mess.service.IStudentService;
import com.ssz.mess.service.IUserService;
import com.ssz.mess.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IStudentService studentService;


    @Override
    @Transactional
    public User checkUser(Integer id, String password) {
        String pwd = MD5Utils.code(password);
        User user = userMapper.getUserWithIdAndPassword(id, pwd);
        if(user == null){
            //说明账号不存在或者密码错误
            return null;
        }
        return user;
    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        if(userMapper.getUserById(user.getId())!=null){
            //已经存在
            return  false;
        }
        return userMapper.insertUser(user) > 0;
    }

    @Override
    @Transactional
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    @Transactional
    public boolean updateUser(Integer id, User user) {
        User u = userMapper.getUserById(id);
        if(u==null){
            //说明不存在
            throw new RuntimeException("id不存在");
        }
        BeanUtils.copyProperties(user,u);
        //更改用户表后，还需要跟新用户相关表
        //不能更新角色
        if(u.getRole() == 1 || u.getRole() == 2){
            //修改店家表
            Store s = storeService.getStoreById(u.getId());
            s.setPassword(u.getPassword());
            storeService.updateStore(u.getId(), s);
        }
        if(u.getRole() == 3){
            //更新学生表
            Student student = studentService.getStudentById(u.getId());
            student.setPassword(u.getPassword());
            studentService.updateStudent(u.getId(), student);
        }
        return userMapper.updateUser(u) > 0;
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }
}

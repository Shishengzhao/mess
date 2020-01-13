package com.ssz.mess.service.impl;

import com.ssz.mess.mapper.StudentMapper;
import com.ssz.mess.pojo.Student;
import com.ssz.mess.pojo.StudentExample;
import com.ssz.mess.pojo.User;
import com.ssz.mess.service.IStudentService;
import com.ssz.mess.service.IUserService;
import com.ssz.mess.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public Student checkStudent(Integer id, String password) {
        String pwd = MD5Utils.code(password);
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andIdEqualTo(id)
                .andPasswordEqualTo(pwd);
        List<Student> students = studentMapper.selectByExample(studentExample);
        if(students.size() == 0){
            //说明不存在
            return null;
        }else{
            return students.get(0);
        }
    }

    @Override
    @Transactional
    public boolean saveStudent(Student student) {
        if(studentMapper.getStudentById(student.getId()) != null){
            //说明已经存在
            return false;
        }else {
            student.setPassword(MD5Utils.code(student.getPassword()));

            //添加到用户表
            User user = new User();
            user.setId(student.getId());
            user.setPassword(student.getPassword());
            user.setRole(student.getRole());
            userService.saveUser(user);

            return studentMapper.insert(student) > 0;
        }
    }

    @Override
    @Transactional
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    @Transactional
    public List<Student> getStudents() {
        return studentMapper.selectByExample(null);
    }

    @Override
    @Transactional
    public boolean updateStudent(Integer id, Student stu) {
        Student student = studentMapper.getStudentById(id);
        if(student == null){
            throw new RuntimeException("id不存在");
        }
        //对stu的密码加密
        stu.setPassword(MD5Utils.code(stu.getPassword()));

        if(!stu.getPassword().equals(student.getPassword())){
            //说明密码改了
            //更新用户表
            User user = userService.getUserById(id);
            user.setPassword(stu.getPassword());
            userService.updateUser(id,user);
        }

        BeanUtils.copyProperties(stu,student);

        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andIdEqualTo(id);
        return studentMapper.updateByExample(student,studentExample) > 0;
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andIdEqualTo(id);

        //删除用户表对应信息
        userService.deleteUser(id);

        studentMapper.deleteByExample(studentExample);
    }

}

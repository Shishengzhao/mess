package com.ssz.mess.service;

import com.ssz.mess.pojo.Student;

import java.util.List;

public interface IStudentService {

    /**
     * 验证学生的账号和密码
     * @param id
     * @param password
     * @return 如果存在返回该学生对象；否则返回null
     */
    Student checkStudent(Integer id, String password);

    boolean saveStudent(Student student);

    Student getStudentById(Integer id);

    List<Student> getStudents();

    boolean updateStudent(Integer id, Student stu);

    void deleteStudent(Integer id);
}

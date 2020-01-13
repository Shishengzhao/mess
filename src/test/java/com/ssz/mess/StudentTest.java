package com.ssz.mess;


import com.ssz.mess.mapper.StudentMapper;
import com.ssz.mess.pojo.Student;
import com.ssz.mess.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private IStudentService studentService;

    @Test
    public void testSaveStu(){
        Student student = new Student();
        student.setId(2001);
//        String pwd = "123456";
//        pwd = MD5Utils.code(pwd);
        student.setPassword("123456");
        student.setName("时晟钊");
        student.setNickname("在路上");
        student.setRole(3);
//        int insert = studentMapper.insert(student);

        boolean b = studentService.saveStudent(student);
        System.out.println(b);
    }

    @Test
    void queryStu(){
//        StudentExample studentExample = new StudentExample();
//        StudentExample.Criteria criteria = studentExample.createCriteria();
//        criteria.andIdEqualTo(1);
//        criteria.andPasswordEqualTo("123456");
//        List<Student> students = studentMapper.selectByExample(studentExample);
//        System.out.println(students);
//        if(students == null){
//
//        }
//        Student s = studentMapper.getStudentById(2);
//        System.out.println(s);

        List<Student> students = studentService.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }

    }

    @Test
    void updateStu(){
//        StudentExample studentExample = new StudentExample();
//        StudentExample.Criteria criteria = studentExample.createCriteria();
//        criteria.andIdEqualTo(3);
//        List<Student> students = studentMapper.selectByExample(studentExample);
//        Student stu = null;
//        if(students != null && students.size() > 0){
//            stu = students.get(0);
//            stu.setNickname("我就是张三");
//            int i = studentMapper.updateByExample(stu, studentExample);
//        }else {
//            System.out.println("不存在");
//        }
//        System.out.println(stu);

        Student student = new Student();
        student.setId(2);
        student.setPassword("123456");
        student.setNickname("张三哈哈哈哈");
        student.setRole(3);
        studentService.updateStudent(2,student);
    }

    @Test
    void deleteStu(){
//        StudentExample studentExample = new StudentExample();
//        StudentExample.Criteria criteria = studentExample.createCriteria();
//        criteria.andIdEqualTo(2);
//        int i = studentMapper.deleteByExample(studentExample);
//        System.out.println(i);

        studentService.deleteStudent(2);
    }

}

package com.ssz.mess.controller;

import com.ssz.mess.pojo.Store;
import com.ssz.mess.pojo.Student;
import com.ssz.mess.pojo.User;
import com.ssz.mess.service.IStoreService;
import com.ssz.mess.service.IStudentService;
import com.ssz.mess.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IStudentService studentService;
//    @GetMapping
//    public String loginPage(){
//        return "index";
//    }

    @PostMapping("/user/login")
    public String login(@RequestParam("username") Integer id,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
        User user = userService.checkUser(id, password);
        System.out.println(user);
        if(user == null){
            String msg = "账号或密码错误";
            map.put("msg",msg);
            System.out.println(msg);
            return "login";
        }
        session.setAttribute("user",user);
        int role = user.getRole();
        switch (role){
            case 1:
                //管理员
                System.out.println("管理员。。。");
                Student admin = studentService.getStudentById(user.getId());
                session.setAttribute("admin",admin);
//                return "redirect:/main.html";
                return "admin";
            case 2:
                //店家
                System.out.println("店家。。。");
                return "store";
            case 3:
                //学生
                System.out.println("学生。。。");
                return "student";
        }
        return "login";
    }

    /**
     * 注销登录的用户
     * @return
     */
    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

}

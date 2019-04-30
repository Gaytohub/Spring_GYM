package com.springboot.gymclub.controller;

import com.springboot.gymclub.entity.User;
import com.springboot.gymclub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    private User user=new User();

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/searchClub")
    public String searchClub(){
        return "searchClub";
    }

    @RequestMapping("/searchTrainer")
    public String searchTrainer(){
        return "searchTrainer";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/urlogin")
    public String urlogin(HttpServletRequest request, HttpSession session){
        Long phone=Long.valueOf(request.getParameter("phone"));
        String password=request.getParameter("password");
        user=userService.findByPhoneAndPassword(phone, password);
        String str="";
        if(user!=null) {
            str="redirect:/index";
        }else {
            str="redirect:/login";
        }
        return str;
    }

    @RequestMapping("/uregister")
    public String register(HttpServletRequest request) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String password2=request.getParameter("password2");
        Long phone=Long.valueOf(request.getParameter("phone"));
        String gender=request.getParameter("gender");
        String str="";
        if(password.equals(password2)) {
            user=userService.findByPhone(phone);
            if(user==null) {
                userService.addUser(username,password,gender,phone);
                str="redirect:/login";
            }else {
                str="redirect:/register";
            }
        }else {
            str="redirect:/register";
        }
        return str;
    }

}

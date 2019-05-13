package com.springboot.gymclub.controller;

import com.springboot.gymclub.entity.User;
import com.springboot.gymclub.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private User user = new User();

    @ApiOperation(value = "登录")
    @RequestMapping(method = RequestMethod.GET,value = "/login")
    public ModelAndView login() {
        return new ModelAndView("/login") ;
    }

    @ApiOperation(value = "注册")
    @RequestMapping(method = RequestMethod.GET,value = "/register")
    public ModelAndView register() {
        return new ModelAndView("/register");
    }

    @ApiOperation(value = "查询俱乐部信息")
    @RequestMapping(method = RequestMethod.GET,value = "/searchClub")
    public ModelAndView searchClub() {
        return new ModelAndView("/searchClub");
    }

    @ApiOperation(value = "查询训练师信息")
    @RequestMapping(method = RequestMethod.GET,value = "/searchTrainer")
    public ModelAndView searchTrainer() {
        return new ModelAndView("/searchTrainer");
    }

    @ApiOperation(value = "index界面跳转")
    @RequestMapping(method = RequestMethod.POST,value = "/index")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @ApiOperation(value = "登录检测")
    @ApiImplicitParams({@ApiImplicitParam(name = "request",value = "Http请求",dataType = "HttpServletRequest"),
            @ApiImplicitParam(name = "session",value = "Http缓存",dataType = "HttpSession")}
    )
    @RequestMapping(method = RequestMethod.POST,value = "/urlogin")
    @Transactional
    public ModelAndView urlogin(HttpServletRequest request, HttpSession session) {
        if (userService.tryAcquireSeckill()) {
            Long phone = Long.valueOf(request.getParameter("phone"));
            String password = request.getParameter("password");
            user = userService.findByPhoneAndPassword(phone, password);
            String str = "";
            if (user != null) {
                str = "/index";
            } else {
                str = "/login";
            }
            return new ModelAndView(str);
        } else {
            return new ModelAndView("/outLimit");
        }
    }


    @ApiOperation(value = "注册检测")
    @ApiImplicitParams(@ApiImplicitParam(name = "request",value = "Http请求",dataType = "HttpServletRequest")

    )
    @RequestMapping(method = RequestMethod.POST,value = "/uregister")
    @Transactional
    public ModelAndView register(HttpServletRequest request) {
        if (userService.tryAcquireSeckill()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            Long phone = Long.valueOf(request.getParameter("phone"));
            String gender = request.getParameter("gender");
            String str = "";
            if (password.equals(password2)) {
                user = userService.findByPhone(phone);
                if (user == null) {
                    userService.addUser(username, password, gender, phone);
                    str = "/login";
                } else {
                    str = "/register";
                }
            } else {
                str = "/register";
            }
            return new ModelAndView(str);
        } else {
            return new ModelAndView("/outLimit");
        }
    }

}

package com.springboot.gymclub.controller;

import com.springboot.gymclub.entity.Trainer;
import com.springboot.gymclub.entity.User;
import com.springboot.gymclub.entity.UserCourse;
import com.springboot.gymclub.kafka.Producer;
import com.springboot.gymclub.service.CourseService;
import com.springboot.gymclub.service.TrainerService;
import com.springboot.gymclub.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TrainerService trainerService;

            private User user = new User();
            private Trainer trainer = new Trainer();



    @ApiOperation(value = "登录初始页")
    @RequestMapping(method = RequestMethod.GET,value = "/login")
    public ModelAndView login() {
        return new ModelAndView("/login") ;
    }

    @ApiOperation(value = "注册")
    @RequestMapping(method = RequestMethod.GET,value = "/register")
    public ModelAndView register() {
        return new ModelAndView("/register");
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping(method = RequestMethod.GET,value = "/userLogin")
    public ModelAndView userLogin() {
        return new ModelAndView("/userLogin") ;
    }

    @ApiOperation(value = "教练登录")
    @RequestMapping(method = RequestMethod.GET,value = "/trainerLogin")
    public ModelAndView trainerLogin() {
        return new ModelAndView("/trainerLogin") ;
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

    @ApiOperation(value = "查询课程信息")
    @RequestMapping(method = RequestMethod.POST,value = "/searchCourse")
    public ModelAndView searchCourse(HttpServletRequest request) {
        String phone = request.getParameter("phone");

        request.setAttribute("userPhone",phone);
        return new ModelAndView("/searchCourse");
    }

    @ApiOperation(value = "index界面跳转")
    @RequestMapping(method = RequestMethod.POST,value = "/index")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @ApiOperation(value = "用户登录检测")
    @ApiImplicitParams({@ApiImplicitParam(name = "request",value = "Http请求",dataType = "HttpServletRequest"),
            @ApiImplicitParam(name = "session",value = "Http缓存",dataType = "HttpSession")}
    )
    @RequestMapping(method = RequestMethod.POST,value = "/urlogin")
    @Transactional
    public ModelAndView urlogin(HttpServletRequest request, HttpSession session) {
        if (userService.tryAcquireSeckill()) {
            Long phone = Long.valueOf(request.getParameter("phone"));
            String password = request.getParameter("password");
            Mono<User> temp;
            temp = userService.findByPhoneAndPassword(phone, password);
            user = temp.block();
            String str = "";
            if (user != null) {
                str = "/userIndex";
                request.setAttribute("userPhone", phone);
            } else {
                str = "/userLogin";
            }
            return new ModelAndView(str);
        } else {
            return new ModelAndView("/outLimit");
        }
    }

    @ApiOperation(value = "教练登录检测")
    @RequestMapping(method = RequestMethod.POST,value = "/trlogin")
    @Transactional
    public ModelAndView trlogin(HttpServletRequest request, HttpSession session) {
        if (trainerService.tryAcquireSeckill()) {
            Long phone = Long.valueOf(request.getParameter("phone"));
            Integer password = Integer.valueOf(request.getParameter("password"));
            Mono<Trainer> temp = trainerService.findByPhoneAndPassword(phone, password);
            String str = "";
            trainer = temp.block();
            if (trainer != null) {
                str = "/trainerIndex";
                request.setAttribute("trainerPhone", phone);
            } else {
                str = "/trainerLogin";
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
                Mono<User> temp;
                temp = userService.findByPhone(phone);
                user = temp.block();
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

    @ApiOperation(value = "用户个人中心")
    @RequestMapping(method = RequestMethod.POST,value = "/userCenter")
    public ModelAndView userCenter(HttpServletRequest request) {
        Long phone = Long.valueOf(request.getParameter("phone"));
        Flux<UserCourse> list = userService.getUserCourse(phone);
        request.setAttribute("results",list);
        return new ModelAndView("/userCenter");
    }

    @ApiOperation(value = "教练个人中心")
    @RequestMapping(method = RequestMethod.POST,value = "/trainerCenter")
    public ModelAndView trainerCenter(HttpServletRequest request) {
        Long phone = Long.valueOf(request.getParameter("phone"));
        Flux<UserCourse> list = trainerService.getUserCourse(phone);
        request.setAttribute("results",list);
        return new ModelAndView("/trainerCenter");
    }

    @Autowired
    private CourseService courseService;
    @Autowired
    private Producer producer;

    @ApiOperation(value = "用户发送申请")
    @RequestMapping(method = RequestMethod.POST,value = "/sendApply")
    public ModelAndView sendApply(HttpServletRequest request) {
        System.out.println("线程"+Thread.currentThread().getName()+"正在运行");
        producer.send(Long.valueOf(request.getParameter("userPhone")),
                request.getParameter("trainerName"),
                Integer.valueOf(request.getParameter("courseId")),
                request.getParameter("courseName"));//执行消息生产
        return new ModelAndView("/OperateSuccess");//跳转提示界面
    }

    @ApiOperation(value = "用户取消申请")
    @RequestMapping(method = RequestMethod.POST,value = "/cancelApply")
    public ModelAndView cancelApply(HttpServletRequest request) {
        System.out.println("线程"+Thread.currentThread().getName()+"正在运行");
        courseService.operate(Integer.valueOf(request.getParameter("id")),"已取消");
        request.setAttribute("userPhone", Long.valueOf(request.getParameter("userPhone")));
        return new ModelAndView("/userIndex");
    }

    @ApiOperation(value = "教练通过申请")
    @RequestMapping(method = RequestMethod.POST,value = "/agreeApply")
    public ModelAndView agreeApply(HttpServletRequest request) {
        System.out.println("线程"+Thread.currentThread().getName()+"正在运行");
        courseService.operate(Integer.valueOf(request.getParameter("id")),"已通过");
        request.setAttribute("trainerPhone", Long.valueOf(request.getParameter("trainerPhone")));
        return new ModelAndView("/trainerIndex");
    }

    @ApiOperation(value = "教练拒绝申请")
    @RequestMapping(method = RequestMethod.POST,value = "/rejectApply")
    public ModelAndView rejectApply(HttpServletRequest request) {
        System.out.println("线程"+Thread.currentThread().getName()+"正在运行");
        courseService.operate(Integer.valueOf(request.getParameter("id")),"已取消");
        request.setAttribute("trainerPhone", Long.valueOf(request.getParameter("trainerPhone")));
        return new ModelAndView("/trainerIndex");
    }
}

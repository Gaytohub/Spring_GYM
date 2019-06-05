package com.springboot.gymclub.controller;

import com.springboot.gymclub.dao.UserCourseDao;
import com.springboot.gymclub.entity.Course;
import com.springboot.gymclub.entity.Trainer;
import com.springboot.gymclub.entity.User;
import com.springboot.gymclub.entity.UserCourse;
import com.springboot.gymclub.service.CourseService;
import com.springboot.gymclub.service.TrainerService;
import com.springboot.gymclub.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "课程搜索结果")
    @RequestMapping(method = RequestMethod.POST, value = "/CourseInfo")
    @Transactional
    public ModelAndView test(HttpServletRequest request) {
        if (courseService.tryAcquireSeckill()) {
            int pageNum ;
            if(request.getParameter("pageNum")== null){
                pageNum = 1;
            }else {
                pageNum = Integer.valueOf(request.getParameter("pageNum"));
            }
            Page<Course> list = null;
            String name = request.getParameter("name");
            String trainer = request.getParameter("trainer");
            int totalPages = courseService.findTotalPage();

            if (name.equals("") && trainer.equals("")) {
                list = courseService.getAllCourses(pageNum);
            } else if (name.equals("")) {
                list = courseService.findByTrainer(trainer,pageNum);
            } else if (trainer.equals("")) {
                list = courseService.findByName(name,pageNum);
            } else {
                list = courseService.findByNameAndTrainer(name,trainer, pageNum);
            }
            if (list.isEmpty()) {
                list = null;
            }
            String phone = request.getParameter("phone");

            request.setAttribute("userPhone",phone);
            request.setAttribute("results", list);
            request.setAttribute("pageNumber", pageNum);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("searchName", name);
            request.setAttribute("searchTrainer", trainer);
            return new ModelAndView("/CourseInfo");
        } else {
            return new ModelAndView("/outLimit");
        }
    }
}

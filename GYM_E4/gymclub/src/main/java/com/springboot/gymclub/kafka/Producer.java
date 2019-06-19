package com.springboot.gymclub.kafka;

import com.springboot.gymclub.entity.UserCourse;
import com.springboot.gymclub.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;


@Service
public class Producer {
    private Gson gson = new GsonBuilder().create();
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private CourseService courseService;


    //当用户发起一个预约申请时，往服务器上叫userCourse的topic生产一条消息
    @Async
    public void send(Long userPhone,String trainerName,Integer courseId,String courseName) {
        System.out.println("线程"+Thread.currentThread().getName()+"正在运行");
        UserCourse userCourse = courseService.buildUserCourse(userPhone,trainerName,courseId,courseName);
        //以json格式发送到topic
        kafkaTemplate.send("userCourse", gson.toJson(userCourse));
    }
}

package com.springboot.gymclub.kafka;

import com.alibaba.fastjson.JSON;
import com.springboot.gymclub.dao.UserCourseDao;
import com.springboot.gymclub.entity.UserCourse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Consumer {
    @Autowired
    private UserCourseDao userCourseDao;

    //监听userCourse这个topic，只要有新消息就会读取，然后把这条预约写入数据库中
    @KafkaListener(topics = "userCourse")
    public void listen(ConsumerRecord<?,String> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            UserCourse userCourse = JSON.parseObject(message.toString(),UserCourse.class);
            userCourseDao.save(userCourse);
        }
    }
}

package com.springboot.gymclub.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userCourses")
public class UserCourse{
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Integer id;//自增的用户课程ID
    @Column(length = 32)
    private String user;
    @Column(length = 32)
    private Long userPhone;
    @Column(length = 32)
    private String trainer;
    @Column(length = 32)
    private Long trainerPhone;
    @Column(length = 32)
    private String course;
    @Column(length = 32)
    private Integer courseId;
    @Column(length = 32)
    private String status;

    public UserCourse(){
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) { this.id = id; }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public Long getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public String getTrainer() { return trainer; }
    public void setTrainer(String trainer) { this.trainer = trainer; }

    public Long getTrainerPhone() {
        return trainerPhone;
    }
    public void setTrainerPhone(Long trainerPhone) {
        this.trainerPhone = trainerPhone;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

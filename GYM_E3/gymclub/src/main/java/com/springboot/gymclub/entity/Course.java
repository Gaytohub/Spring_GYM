package com.springboot.gymclub.entity;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Integer id;//自增的课程ID
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String trainer;
    @Column(length = 256)
    private String intro;

    public Course(){

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTrainer() { return trainer; }
    public void setTrainer(String trainer) { this.trainer = trainer; }

    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
}

package com.springboot.gymclub.res;

import com.springboot.gymclub.entity.Trainer;
import org.springframework.hateoas.Resource;

public class TrainerResource extends Resource {
    private Long phone;
    private String name;
    private String gender;
    private int age;
    private String club;
    private String intro;
    private String selfUrl;
    private String clubUrl;

    public TrainerResource(Trainer trainer) {
        super(trainer);
        this.phone = trainer.getPhone();
        this.name = trainer.getName();
        this.gender = trainer.getGender();
        this.age = trainer.getAge();
        this.club = trainer.getClub();
        this.intro = trainer.getIntro();
        this.selfUrl = trainer.getLinks().get(0).getHref().substring(21);
        this.clubUrl = trainer.getLinks().get(1).getHref().substring(21);
    }
    public Long getPhone(){
        return phone;
    }
    public String getName(){
        return name;
    }
    public String getGender(){
        return gender;
    }
    public int getAge(){
        return age;
    }
    public String getClub(){
        return club;
    }
    public String getIntro(){
        return intro;
    }
    public String getSelfUrl(){
        return selfUrl;
    }
    public String getClubUrl(){
        return clubUrl;
    }
}

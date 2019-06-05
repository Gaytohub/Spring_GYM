package com.springboot.gymclub.res;

import com.springboot.gymclub.entity.Club;
import org.springframework.hateoas.Resource;

public class ClubResource extends Resource {
    private Long phone;
    private String name;
    private String address;
    private String intro;
    private String selfUrl;

    public ClubResource(Club club) {
        super(club);
        this.phone = club.getPhone();
        this.name = club.getName();
        this.address = club.getAddress();
        this.intro = club.getIntro();
        this.selfUrl = club.getLinks().get(0).getHref().substring(21);
    }
    public Long getPhone(){
        return phone;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getIntro(){
        return intro;
    }
    public String getSelfUrl(){
        return selfUrl;
    }
}

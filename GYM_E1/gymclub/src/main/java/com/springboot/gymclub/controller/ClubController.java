package com.springboot.gymclub.controller;

import com.springboot.gymclub.entity.Club;
import com.springboot.gymclub.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ClubController {
    @Autowired
    private ClubService clubService;

    @GetMapping("/clubs")
    public List<Club> getAllClubs()
    {
        return clubService.getAllClubs();
    }

    @RequestMapping(value = "/ClubInfo")
    public String getTrainers(HttpServletRequest request) {
        List<Club> list;
        String name = request.getParameter("name");
        System.out.println(name);
        String temp = request.getParameter("phone");
        if(name.equals("")&&temp.equals("")) {
            list = clubService.getAllClubs();
        }else if (name.equals("")) {
            Long phone = (long) (Double.parseDouble(temp));
            System.out.println(phone);
            list = clubService.findByPhone(phone);
        } else if (temp.equals("")) {
            list = clubService.findByName(name);
        } else {
            Long phone = (long) (Double.parseDouble(temp));
            list = clubService.findByNameAndPhone(name, phone);
        }
        if (list.isEmpty()){
            list = null;
        }
        request.setAttribute("results", list);
        return "ClubInfo";
    }
}

package com.springboot.gymclub.controller;

import com.springboot.gymclub.entity.Trainer;
import com.springboot.gymclub.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @RequestMapping(value = "/TrainerInfo")
    public String getTrainers(HttpServletRequest request) {
        List<Trainer> list = null;
        String club = request.getParameter("club");
        String gender = request.getParameter("gender");
        System.out.println(club+gender);
        if (gender.equals("") && club.equals("")) {
            list = trainerService.getAllTrainers();

        } else if (gender.equals("")) {
            list = trainerService.findByClub(club);
        } else if (club.equals("")) {
            list = trainerService.findByGender(gender);
        } else {
            list = trainerService.findByClubAndGender(club, gender);
        }
        if (list.isEmpty()) {
            list = null;
        }

//        request.setAttribute("club" ,club);
//        request.setAttribute("gender",gender);
        request.setAttribute("results", list);
        request.setAttribute("pageNum",  1);
        return "TrainerInfo";
    }

//    @RequestMapping(value = "/NextTrainerInfo")
//    public String getNextTrainers(HttpServletRequest request) {
//        Page<Trainer> list = null;
//        String club = request.getParameter("club");
//        String gender = request.getParameter("gender");
//        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
//        if (gender.equals("") && club.equals("")) {
//            list = trainerService.getAllTrainers(pageNum+1, 2);
//
//        }
//        else if (gender.equals("")) {
//            list = trainerService.findByClub(pageNum+1,2,club);
//        } else if (club.equals("")) {
//            list = trainerService.findByGender(pageNum+1,2,gender);
//        } else {
//            list = trainerService.findByClubAndGender(pageNum+1,2,club, gender);
//        }
//        if (list.isEmpty()) {
//            list = null;
//        }
//
//        request.setAttribute("club" ,club);
//        request.setAttribute("gender",gender);
//        request.setAttribute("results", list);
//        request.setAttribute("pageNum",  pageNum+1);
//        return "TrainerInfo";
//    }
//
//    @RequestMapping(value = "/PreTrainerInfo")
//    public String getPreTrainers(HttpServletRequest request) {
//        Page<Trainer> list = null;
//        String club = request.getParameter("club");
//        String gender = request.getParameter("gender");
//        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
//        if (gender.equals("") && club.equals("")) {
//            list = trainerService.getAllTrainers(pageNum-1, 2);
//
//        } else if (gender.equals("")) {
//            list = trainerService.findByClub(pageNum-1,2,club);
//        } else if (club.equals("")) {
//            list = trainerService.findByGender(pageNum-1,2,gender);
//        } else {
//            list = trainerService.findByClubAndGender(pageNum-1,2,club, gender);
//        }
//        if (list.isEmpty()) {
//            list = null;
//        }
//
//        request.setAttribute("club" ,club);
//        request.setAttribute("gender",gender);
//        request.setAttribute("results", list);
//        request.setAttribute("pageNum",  pageNum-1);
//        return "TrainerInfo";
//    }
}
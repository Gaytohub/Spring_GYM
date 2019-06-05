package com.springboot.gymclub.controller;

import com.springboot.gymclub.entity.Trainer;
import com.springboot.gymclub.res.TrainerResource;
import com.springboot.gymclub.service.TrainerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TrainerController implements LastModified {

    @Autowired
    private TrainerService trainerService;

    private long lastModified = System.currentTimeMillis();


    @ApiOperation(value = "查询训练师信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "webRequest",value = "web请求",dataType = "WebRequest"),
            @ApiImplicitParam(name = "request",value = "Http请求",dataType = "HttpServletRequest")}
    )
    @RequestMapping("/TrainerInfo")
    @Transactional
    public ModelAndView getTrainers(WebRequest webRequest,HttpServletRequest request) {
        System.out.println("start");
        if(webRequest.checkNotModified(lastModified)){
            System.out.println("check : "+lastModified);
            return null;
        }
        System.out.println("no check : "+lastModified);

        if (trainerService.tryAcquireSeckill()) {
            int pageNum ;
            if(request.getParameter("pageNum")== null){
                pageNum = 1;
            }else {
                pageNum = Integer.valueOf(request.getParameter("pageNum"));
            }
            Page<Trainer> list = null;
            String club = request.getParameter("club");
            String gender = request.getParameter("gender");
            int totalPages = trainerService.findTotalPage();

            if (gender.equals("") && club.equals("")) {
                list = trainerService.getAllTrainers(pageNum);
            } else if (gender.equals("")) {
                list = trainerService.findByClub(club,pageNum);
            } else if (club.equals("")) {
                list = trainerService.findByGender(gender,pageNum);
            } else {
                list = trainerService.findByClubAndGender(club, gender,pageNum);
            }
            if (list.isEmpty()) {
                list = null;
            }

            List<TrainerResource> list2 = new ArrayList<TrainerResource>();
            for(Trainer t:list){
                t.add(new Link("http://localhost:8080/trainerDetail"));
                t.add(new Link("http://localhost:8080/clubDetail"));
                list2.add(new TrainerResource(t));
            }
            request.setAttribute("results", list2);
            request.setAttribute("pageNumber", pageNum);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("searchClub", club);
            request.setAttribute("searchGender", gender);
            return new ModelAndView("/TrainerInfo");
        } else {
            return new ModelAndView("/outLimit");
        }
    }


    @ApiOperation(value = "查询训练师详细信息")
    @ApiImplicitParams(@ApiImplicitParam(name = "request",value = "Http请求",dataType = "HttpServletRequest")
    )
    @RequestMapping(method = RequestMethod.POST, value = "/trainerDetail")
    @Transactional
    public ModelAndView selfTrainer(HttpServletRequest request) {
        if (trainerService.tryAcquireSeckill()) {
            Long phone = Long.valueOf(request.getParameter("condition"));
            Trainer t = trainerService.findByPhone(phone);
            t.add(new Link("http://localhost:8080/trainerDetail"));
            t.add(new Link("http://localhost:8080/clubDetail"));
            TrainerResource tr = new TrainerResource(t);
            request.setAttribute("trainer",tr);
            return new ModelAndView("/trainerDetail");
        } else {
            return new ModelAndView("/outLimit");
        }
    }

    @Override
    public long getLastModified(HttpServletRequest httpServletRequest) {
        return lastModified;
    }
}
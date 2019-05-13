package com.springboot.gymclub.controller;

import com.springboot.gymclub.entity.Club;
import com.springboot.gymclub.res.ClubResource;
import com.springboot.gymclub.service.ClubService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClubController implements LastModified {
    @Autowired
    private ClubService clubService;
    private long lastModified = System.currentTimeMillis();

//    @RequestMapping(method = RequestMethod.POST,value = "/ClubInfo")
//    @Transactional
//    public ModelAndView getTrainers(HttpServletRequest request) {
//        if (clubService.tryAcquireSeckill()) {
//            List<Club> list;
//            String name = request.getParameter("name");
//            System.out.println(name);
//            String temp = request.getParameter("phone");
//            if (name.equals("") && temp.equals("")) {
//                list = clubService.getAllClubs();
//            } else if (name.equals("")) {
//                Long phone = (long) (Double.parseDouble(temp));
//                System.out.println(phone);
//                list = clubService.findByPhone(phone);
//            } else if (temp.equals("")) {
//                list = clubService.findByName(name);
//            } else {
//                Long phone = (long) (Double.parseDouble(temp));
//                list = clubService.findByNameAndPhone(name, phone);
//            }
//            if (list.isEmpty()) {
//                list = null;
//            }
//
//            List<ClubResource> list2 = new ArrayList<ClubResource>();
//            for(Club c:list){
//                c.add(new Link("http://localhost:8080/clubDetail"));
//                list2.add(new ClubResource(c));
//            }
//            request.setAttribute("results", list2);
//            return new ModelAndView("/ClubInfo");
//        } else {
//            return new ModelAndView("/outLimit");
//        }
//    }

    @ApiOperation(value = "返回搜索club详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "webRequest",value = "web请求",dataType = "WebRequest"),
                   @ApiImplicitParam(name = "request",value = "Http请求",dataType = "HttpServletRequest")})
    @RequestMapping(value = "/ClubInfo")
    @Transactional
    public ModelAndView test(WebRequest webRequest, HttpServletRequest request) {
        System.out.println("start");
        if(webRequest.checkNotModified(lastModified)){
            System.out.println("check : "+lastModified);
            return null;
        }
        System.out.println("no check : "+lastModified);

        if (clubService.tryAcquireSeckill()) {
            int pageNum ;
            if(request.getParameter("pageNum")== null){
                pageNum = 1;
            }else {
                pageNum = Integer.valueOf(request.getParameter("pageNum"));
            }
            Page<Club> list = null;
            String name = request.getParameter("name");
            String temp = request.getParameter("phone");
            int totalPages = clubService.findTotalPage();

            if (name.equals("") && temp.equals("")) {
                list = clubService.getAllClubs(pageNum);
            } else if (name.equals("")) {
                Long phone = (long) (Double.parseDouble(temp));
                System.out.println(phone);
                list = clubService.findByPhone(phone,pageNum);
            } else if (temp.equals("")) {
                list = clubService.findByName(name,pageNum);
            } else {
                Long phone = (long) (Double.parseDouble(temp));
                list = clubService.findByNameAndPhone(name, phone,pageNum);
            }
            if (list.isEmpty()) {
                list = null;
            }
            List<ClubResource> list2 = new ArrayList<ClubResource>();
            for(Club c:list){
                c.add(new Link("http://localhost:8080/clubDetail"));
                list2.add(new ClubResource(c));
            }
            request.setAttribute("results", list2);
            request.setAttribute("pageNumber", pageNum);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("searchName", name);
            request.setAttribute("searchPhone", temp);
            return new ModelAndView("/ClubInfo");
        } else {
            return new ModelAndView("/outLimit");
        }
    }


    @ApiOperation(value = "俱乐部跳转详情")
    @ApiImplicitParams(@ApiImplicitParam(name = "request",value = "Http请求",dataType = "HttpServletRequest")
            )
    @RequestMapping(method = RequestMethod.POST, value = "/clubDetail")
    @Transactional
    public ModelAndView selfClub(HttpServletRequest request) {
        if (clubService.tryAcquireSeckill()) {
            String name = request.getParameter("condition");
            Club c = clubService.findByName(name);//严谨的写法是在教练表里加一个所在俱乐部的联系方式或者干脆用俱乐部名字做主键
            request.setAttribute("club",c);
            return new ModelAndView("/clubDetail");
        } else {
            return new ModelAndView("/outLimit");
        }
    }

    @Override
    public long getLastModified(HttpServletRequest httpServletRequest) {
        return lastModified;
    }
}

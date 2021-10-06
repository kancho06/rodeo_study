package com.sparta.rodeo.controller;


import com.sparta.rodeo.models.UserRoleEnum;
import com.sparta.rodeo.security.UserDetailsImpl;



import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomeController {
    //유저값 뿌려주기
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "../static/index.html";
    }


    @GetMapping("/goPost")
    public String postPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());

        }
        return "../static/post.html";


    }
    @GetMapping("/detail.html")
    public String detail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "../static/detail.html";
    }

//    @GetMapping("/goPost")
//    public ModelAndView Aut( @AuthenticationPrincipal UserDetailsImpl userDetails ) throws NotFoundException {
//        ModelAndView mav = new ModelAndView("../static/index.html");
//        ModelAndView mv = new ModelAndView("../static/post.html");
//        if (userDetails == null) {
//            mav.addObject("errorAlert","로그인을 해주세요.");
//            return mav;
//        } else {
//            mv.addObject("username",userDetails.getUsername());
//        }
//        return mv;

    }



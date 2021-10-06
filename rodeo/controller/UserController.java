package com.sparta.rodeo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.rodeo.dto.SignupRequestDto;
import com.sparta.rodeo.repository.UserRepository;
import com.sparta.rodeo.security.UserDetailsImpl;
import com.sparta.rodeo.service.KakaoUserService;
import com.sparta.rodeo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;


    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService, UserRepository userRepository) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;

    }

    // 회원 로그인 페이지 (유저정보 내려가는것 확인)
    @GetMapping("/user/login")
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());

        }

        return "../static/login.html";
    }

    // 회원 가입 페이지 (유저정보 내려가는것 확인)
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());

        }


        return "../static/signup.html";
    }

//    @GetMapping("/user/login/error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "../static/login.html";
//    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto, Model model) {
        if (userService.registerUser(requestDto) == ""){
            userService.registerUser(requestDto);
            return "../static/login.html";
        }else {
            model.addAttribute("errortext",userService.registerUser(requestDto));
            return "../static/signup.html";
        }
    }




    // 회원 가입 요청 처리

//    @PostMapping("/user/signup")
//    public String registerUser(SignupRequestDto requestDto, Model model) {
//        String username = requestDto.getUsername();
//        String password = requestDto.getPassword();
//        String passwordCheck = requestDto.getPasswordCheck();
//        Optional<User> found = userRepository.findByUsername(username);
//        String pattern = "^[a-zA-Z0-9]*$";
//        if (found.isPresent()) {
//            model.addAttribute("error1","중복된 닉네임입니다.");
//            return "signup";
//        } else if (username.length() < 3 ) {
//            model.addAttribute("error2", "아이디는 3글자 이상입니다");
//            return "signup";
//        } else if (!username.matches(pattern)) {
//            model.addAttribute("error3", "아이디는 특수문자제외입니다");
//            return "signup";
//        } else if (!password.equals(passwordCheck)) {
//            model.addAttribute("error4","패스워드 확인과 일치하지 않습니다");
//            return "signup";
//        } else if (!password.matches(pattern)) {
//            model.addAttribute("error5","비밀번호는 특수문자제외입니다.");
//            return "signup";
//        } else if (password.length() < 4) {
//            model.addAttribute("error6","비밀번호는 4글자 이상입니다.");
//            return "signup";
//        } else if (password.contains(username)) {
//            model.addAttribute("error7","아이디 문자열이 포함될 수 없습니다.");
//            return "signup";
//        } else {
//            userService.registerUser(requestDto);
//
//        }
//        return "login";
//    }







    //인가토큰을 받을 곳
   @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:../static/index.html";
    }
}
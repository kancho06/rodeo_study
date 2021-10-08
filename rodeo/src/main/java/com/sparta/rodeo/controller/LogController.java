package com.sparta.rodeo.controller;

import com.sparta.rodeo.models.Log;

import com.sparta.rodeo.dto.LogRequestDto;

import com.sparta.rodeo.security.UserDetailsImpl;
import com.sparta.rodeo.service.LogService;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController

public class LogController {

    private final LogService logService;

    @Autowired
    public LogController (LogService logService) {
        this.logService =logService;
    }


    //포스트생성 xss처리
    @PostMapping("/api/logs")
    public Log createLog(@RequestBody LogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        Log log = logService.createLog(requestDto,username);
        return log;
    }
    //메인페이지 전체글리스트
    @GetMapping("/api/logs")
    public List<Log> getLog(){
        return logService.getLogs();
    }

    //상세페이지 삭제
    @DeleteMapping("/api/logs/{id}")
    public boolean deleteLog(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            logService.deleteById(id, userDetails);
            return true;
        }
        return false;
    }
    //상세페이지 수정
    @PutMapping("/api/logs/{id}")
    public boolean updateLog(@PathVariable Long id, @RequestBody LogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            logService.update(id, requestDto, userDetails);
            return true;
        }
        return false;
    }
    //상세페이지 조회
    @GetMapping("/api/logs/{id}")
    public Log getOneLog(@PathVariable Long id ) {

        Log log = logService.findById(id);

        return log;
    }



}

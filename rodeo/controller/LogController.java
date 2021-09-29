package com.sparta.rodeo.controller;

import com.sparta.rodeo.models.Log;
import com.sparta.rodeo.models.LogRepository;
import com.sparta.rodeo.models.LogRequestDto;
import com.sparta.rodeo.service.LogService;
import lombok.RequiredArgsConstructor;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class LogController {
    private final LogRepository logRepository;
    private final LogService logService;


    //포스트생성 xss처리
    @PostMapping("/api/logs")
    public Log createLog(@RequestBody LogRequestDto requestDto) {
        Log log = logService.createLog(requestDto);
        return log;
    }
    //메인페이지 전체글리스트
    @GetMapping("/api/logs")
    public List<Log> getLog(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return logRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(yesterday, now);
    }
    //상세페이지 삭제
    @DeleteMapping("/api/logs/{id}")
    public Long deleteLog(@PathVariable Long id) {
        logRepository.deleteById(id);
        return id;
    }
    //상세페이지 수정
    @PutMapping("/api/logs/{id}")
    public Long updateLog(@PathVariable Long id, @RequestBody LogRequestDto requestDto) {
        logService.update(id, requestDto);
        return id;
    }
    //상세페이지 조회
    @GetMapping("/api/logs/{id}")
    public Log getOneLog(@PathVariable Long id) {
        Log log = logRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시물을 찾을 수 없습니다.")
        );
        return log;
    }


}

package com.sparta.rodeo.service;

import com.sparta.rodeo.models.Log;
import com.sparta.rodeo.repository.LogRepository;
import com.sparta.rodeo.dto.LogRequestDto;

import com.sparta.rodeo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Transactional
    public Long update(Long id, LogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Log log = logRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디를 찾을 수 없습니다.")
        );
        if (log.getUsername().equals(userDetails.getUsername())) {
            log.update(requestDto);
            return id;
        } else {
            return null;
        }

    }

    @Transactional
    public Log createLog(LogRequestDto requestDto, String username) {

        String contentsCheck = requestDto.getContents();
        String categoryCheck = requestDto.getCategory();
        String titleCheck = requestDto.getTitle();

        if (titleCheck.contains("script")||titleCheck.contains("<")||titleCheck.contains(">")) {
            Log log = new Log(requestDto, "스탑","스탑","스탑");
            logRepository.save(log);
            return log;
        }
        if (contentsCheck.contains("script")||contentsCheck.contains("<")||contentsCheck.contains(">")){
            Log log = new Log(requestDto,"스탑","스탑","스탑");
            logRepository.save(log);
            return log;
        }
        if (categoryCheck.contains("script")||categoryCheck.contains("<")||categoryCheck.contains(">")){
            Log log = new Log(requestDto,"스탑","스탑","스탑");
            logRepository.save(log);
            return log;
        }

        Log log = new Log(requestDto, username);
        logRepository.save(log);
        return log;
    }

    public List<Log> getLogs() {

        return logRepository.findAllByOrderByModifiedAtDesc();
    }
    public void deleteById(Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Log log = logRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디를 찾을 수 없습니다.")
        );
        if(log.getUsername().equals(userDetails.getUsername())) {
            logRepository.deleteById(id);
        }
        return;
    }

    public Log findById(Long id) {
        Log log =logRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시물을 찾을 수 없습니다.")
        );
        return log;

    }


}

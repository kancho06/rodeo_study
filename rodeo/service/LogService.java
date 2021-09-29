package com.sparta.rodeo.service;

import com.sparta.rodeo.models.Log;
import com.sparta.rodeo.models.LogRepository;
import com.sparta.rodeo.models.LogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    @Transactional
    public Long update(Long id, LogRequestDto requestDto){
        Log log = logRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디를 찾을 수 없습니다.")
        );
        log.update(requestDto);
        return id;
    }

    @Transactional
    public Log createLog(LogRequestDto requestDto) {
        String usernameCheck = requestDto.getUsername();
        String contentsCheck = requestDto.getContents();
        String categoryCheck = requestDto.getCategory();
        String titleCheck = requestDto.getTitle();
        if (usernameCheck.contains("script")||usernameCheck.contains("<")||usernameCheck.contains(">")){
            Log log = new Log(requestDto,"스탑","스탑","스탑");
            logRepository.save(log);
            return log;
        }
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

        Log log = new Log(requestDto);
        logRepository.save(log);
        return log;
    }


}

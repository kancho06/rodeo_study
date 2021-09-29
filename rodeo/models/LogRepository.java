package com.sparta.rodeo.models;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface LogRepository extends JpaRepository<Log, Long> {
    //findALL 다찾아주고 ByOrderBy 정렬시켜라 ModifiedAt 시간을 기준으로 Desc 내림차순 (최근순)
    List<Log> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);
}


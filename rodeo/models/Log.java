package com.sparta.rodeo.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Log extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

//    @Column(nullable = false)
//    private String image;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String category;

    //포스트용 빵틀
    public Log (LogRequestDto requestDto) {
//        this.image = requestDto.getImage();
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.category = requestDto.getCategory();
    }

    //수정용 빵틀
    public void update(LogRequestDto requestDto) {

        this.contents = requestDto.getContents();

    }

    //걸러내기 xss

    public Log(LogRequestDto requestDto, String title,String username,String contents) {
        this.title = title;
        this.username = username;
        this.contents = contents;
    }






}

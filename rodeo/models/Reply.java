package com.sparta.rodeo.models;


import com.sparta.rodeo.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reply extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long logId;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String username;

    public Reply(ReplyRequestDto requestDto, String username) {
        this.comment = requestDto.getComment();
        this.username = username;
        this.logId = requestDto.getLogId();

    }

    public void update(ReplyRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

    public Reply(ReplyRequestDto requestDto, String username, String comment, Long logId) {
        this.comment = comment;
        this.username = username;
        this.logId = logId;
    }
}

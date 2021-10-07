package com.sparta.rodeo.controller;



import com.sparta.rodeo.dto.ReplyRequestDto;
import com.sparta.rodeo.models.Reply;
import com.sparta.rodeo.security.UserDetailsImpl;
import com.sparta.rodeo.service.ReplyService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this. replyService = replyService;
    }


    //지금 문제제
    //equestDto 에 프론트에서온 logId를 받아서 저장해 준다 나중에 log 디테일 페이지에서 사용되기위함
    @PostMapping("/api/replys")
    public Reply createReply(@RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            String username = userDetails.getUser().getUsername();
            Reply reply = replyService.createReply(requestDto, username);
            return reply;
        }
        return null;
    }


    //댓글을 작성했을때 받은 logId 를 찾아서 그 포스트의 리플을 요구
    @GetMapping("/api/replys/{logId}")
    public List<Reply> getReplys(@PathVariable Long logId) {

        return replyService.findByLogId(logId);
    }

    @DeleteMapping("/api/replys/{id}")
    public boolean deleteReply(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        if (userDetails != null) {
            replyService.deleteReply(userDetails, id);
            return true;
        }
        return false;
    }

    @PutMapping("/api/replys/{id}")
    public boolean updateReply(@PathVariable Long id, @RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            replyService.updateReply(userDetails, requestDto, id);

            return true;
        }
        return false;
    }


}

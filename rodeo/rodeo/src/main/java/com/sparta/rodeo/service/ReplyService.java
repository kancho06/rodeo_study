package com.sparta.rodeo.service;



import com.sparta.rodeo.dto.ReplyRequestDto;

import com.sparta.rodeo.models.Reply;
import com.sparta.rodeo.repository.ReplyRepository;
import com.sparta.rodeo.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {

        this.replyRepository = replyRepository;
    }

    public Reply findById(Long id) {
         Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시물을 찾을 수 없습니다.")
        );
        return reply;
    }


    public List<Reply> findByLogId(Long logId) {
        return replyRepository.findAllBylogIdOrderByCreatedAtDesc(logId);
    }

    @Transactional
    public Reply createReply(ReplyRequestDto requestDto, String username) {
        String commentCheck = requestDto.getComment();

        if (commentCheck.contains("script")||commentCheck.contains("<")||commentCheck.contains(">")) {
            Reply reply = new Reply(requestDto, "스탑");
            replyRepository.save(reply);
            return reply;
        }

        Reply reply = new Reply(requestDto,username);
        replyRepository.save(reply);
        return reply;

    }
    @Transactional
    public Long updateReply(@AuthenticationPrincipal UserDetailsImpl userDetails, ReplyRequestDto requestDto,Long id ) {

        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다.")
        );
        if (reply.getUsername().equals(userDetails.getUsername())) {
            reply.update(requestDto);
            return id;
        } else {
            return null;
        }
    }

        public Long deleteReply(@AuthenticationPrincipal UserDetailsImpl userDetails, Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다.")
        );
        if (reply.getUsername().equals(userDetails.getUsername())) {
            replyRepository.deleteById(id);
            return id;
        }
            return null;

    }
}


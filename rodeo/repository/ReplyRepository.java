package com.sparta.rodeo.repository;



import com.sparta.rodeo.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllBylogIdOrderByCreatedAtDesc(Long logId);
}

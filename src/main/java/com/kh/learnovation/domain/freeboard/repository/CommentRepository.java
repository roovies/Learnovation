package com.kh.learnovation.domain.freeboard.repository;

import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // select * from comment_table where board_id=? order by id desc;
    List<CommentEntity> findAllByFreeBoardEntityOrderByIdDesc(FreeBoardEntity freeBoardEntity);

    List<CommentEntity> findByFreeBoardEntityOrderByCreatedAtDesc(FreeBoardEntity freeBoardEntity);
}
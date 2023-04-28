package com.kh.learnovation.domain.freeboard.repository;

import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.entity.LikeEntity;
import com.kh.learnovation.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {


    @Query("SELECT l FROM LikeEntity l WHERE l.user = :user AND l.freeBoardEntity = :freeBoardEntity")
    Optional<LikeEntity> findByUserAndFreeBoard(@Param("user") User user, @Param("freeBoardEntity") FreeBoardEntity freeBoardEntity);



}

package com.kh.learnovation.domain.freeboard.repository;

import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FreeBoardRepository extends JpaRepository<FreeBoardEntity, Long>{

    // update board_table set board_hits=board_hits+1 where id=?
    @Modifying
    @Query(value = "update FreeBoardEntity f set f.freeBoardHits=f.freeBoardHits+1 where f.id=:id")
    void updateHits(@Param("id") Long id);

    Page<FreeBoardEntity> findByFreeBoardTitleContaining(String searchKeyword, Pageable pageable);

    @Query("SELECT COUNT(l) FROM LikeEntity l WHERE l.freeBoardEntity.id = :freeBoardId")
    long countLikesByFreeBoardId(@Param("id") Long freeBoardId);

}

package com.kh.learnovation.domain.meeting.repository;

import com.kh.learnovation.domain.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Query(nativeQuery = true, value = "select m.* from meeting m join meeting_member b on m.id = b.meeting_id where user_id = :userid")
    List<Meeting> findAllByUserNo(@Param("userid") long userNo);

}

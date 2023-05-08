package com.kh.learnovation.domain.meeting.repository;

import com.kh.learnovation.domain.meeting.entity.Meeting;
import com.kh.learnovation.domain.meeting.entity.MeetingChatting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingChattingRepository extends JpaRepository<MeetingChatting, Long> {
    List<MeetingChatting> findByMeeting(Meeting meeting);
}

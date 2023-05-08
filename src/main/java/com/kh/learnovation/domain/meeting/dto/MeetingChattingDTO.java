package com.kh.learnovation.domain.meeting.dto;

import com.kh.learnovation.domain.meeting.entity.Meeting;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class MeetingChattingDTO {

    private long id;
    private Meeting meeting;
    private User user;
    private Timestamp createAt;
    private String content;
}

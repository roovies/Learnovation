package com.kh.learnovation.domain.meeting.dto;

import com.kh.learnovation.domain.meeting.entity.Meeting;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
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

    @Builder
    public MeetingChattingDTO(long id, Meeting meeting, User user, Timestamp createAt, String Content){
        this.id = id;
        this.meeting = meeting;
        this.user = user;
        this.createAt = createAt;
        this.content = Content;
    }

    @Override
    public String toString() {
        return "MeetingChattingDTO{" +
                "id=" + id +
                ", meeting=" + meeting +
                ", user=" + user +
                ", createAt=" + createAt +
                ", content='" + content + '\'' +
                '}';
    }
}

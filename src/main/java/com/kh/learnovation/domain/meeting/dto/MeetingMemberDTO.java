package com.kh.learnovation.domain.meeting.dto;

import com.kh.learnovation.domain.meeting.entity.Meeting;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class MeetingMemberDTO {
    private Timestamp lastView;
    private User user;
    private Meeting meeting;

    @Builder
    public MeetingMemberDTO(Timestamp lastView, User user, Meeting meeting){
        this.lastView = lastView;
        this.user = user;
        this.meeting = meeting;
    }

    @Override
    public String toString() {
        return "MeetingMemberDTO{" +
                "lastView=" + lastView +
                ", user=" + user +
                ", meeting=" + meeting +
                '}';
    }
}

package com.kh.learnovation.domain.meeting.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "meeting_member")
public class MeetingMember {

    @EmbeddedId
    private MeetingMemberPk meetingMemberPk;
    @Column(name="LAST_VIEW")
    private Timestamp lastView;

    @Builder
    public MeetingMember(MeetingMemberPk meetingMemberPk, Timestamp lastView){
        this.meetingMemberPk = meetingMemberPk;
        this.lastView = lastView;
    }

    @Override
    public String toString() {
        return "MeetingMember{" +
                "meetingMemberPk=" + meetingMemberPk +
                ", lastView=" + lastView +
                '}';
    }
}

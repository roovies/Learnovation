package com.kh.learnovation.domain.meeting.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class MeetingMemberPk implements Serializable {

    @JoinColumn(name ="MEETING_ID")
    @ManyToOne
    private Meeting meeting;
    @JoinColumn(name ="USER_ID")
    @ManyToOne
    private User user;

    @Builder
    public MeetingMemberPk(Meeting meeting, User user){
        this.meeting = meeting;
        this.user = user;
    }
}

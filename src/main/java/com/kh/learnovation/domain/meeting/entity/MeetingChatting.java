package com.kh.learnovation.domain.meeting.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "meeting_chatting")
public class MeetingChatting {
    @Id
    @Column(name="ID")
    @GeneratedValue
    private long id;
    @JoinColumn(name = "METTING_ID")
    @ManyToOne
    private Meeting meeting;
    @JoinColumn(name="USER_ID")
    @ManyToOne
    private User user;
    @Column(name="WRITE_DATE")
    @CreationTimestamp
    private Timestamp createAt;
    @Column(name="CONTENT")
    private String content;

    @Builder
    public MeetingChatting(long id, Meeting meeting, User user, Timestamp createAt, String content){
        this.id = id;
        this.meeting = meeting;
        this.user = user;
        this.createAt = createAt;
        this.content = content;
    }

    @Override
    public String toString() {
        return "MeetingChatting{" +
                "id=" + id +
                ", meeting=" + meeting +
                ", user=" + user +
                ", createAt=" + createAt +
                ", content='" + content + '\'' +
                '}';
    }
}

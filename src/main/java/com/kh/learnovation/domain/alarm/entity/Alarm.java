package com.kh.learnovation.domain.alarm.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "ALARM")
public class Alarm {
    @Id
    @GeneratedValue
    @Column
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="content")
    private String content;
    @Column(name="status")
    private int status;

    @Builder
    public Alarm(long id, User user, String content, int status){
        this.id = id;
        this.user = user;
        this.content = content;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}

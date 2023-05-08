package com.kh.learnovation.domain.matching.entity;

import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name="MATCHING_BOARD")
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
    @Column(name="TITLE", nullable = false)
    private String title;
    @Column(name="CONTENT", nullable = false)
    private String content;
    @Column(name="SUBJECT")
    private String subject;
    @Column(name="CREATE_AT")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name="STATUS")
    private int status;

    @Builder
    public Matching(long id, User user ,String title, String content, String subject, Timestamp createdAt, int status){
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.createdAt = createdAt;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Matching{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                '}';
    }
}

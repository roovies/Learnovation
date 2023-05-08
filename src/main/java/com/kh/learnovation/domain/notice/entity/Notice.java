package com.kh.learnovation.domain.notice.entity;


import com.kh.learnovation.domain.user.entity.User;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name="notices")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="admin_id")
    private User user;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="content", nullable = false)
    private String content;
    @Column(name="created_at")
    private Timestamp createdAt;
    @Column(name="updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @Column(name="status")
    private int status;
    @Column
    private String subject;

    @Builder
    public Notice(long id, User user, String title, String content, Timestamp createdAt, Timestamp updatedAt, int status, String subject){
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                ", subject=" + subject +
                '}';
    }
}

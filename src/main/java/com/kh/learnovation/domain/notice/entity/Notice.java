package com.kh.learnovation.domain.notice.entity;

import com.kh.learnovation.domain.admin.entity.Admin;
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
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="admin_id")
    private Admin admin;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="content", nullable = false)
    private String content;
    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name="updated_at")
    @UpdateTimestamp
    private Timestamp updateAt;

    @Builder
    public Notice(long id, Admin admin, String title, String content, Timestamp createdAt, Timestamp updateAt){
        this.id = id;
        this.admin = admin;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
}

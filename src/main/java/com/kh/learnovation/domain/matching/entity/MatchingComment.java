package com.kh.learnovation.domain.matching.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="MATCHING_COMMENT")
public class MatchingComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name="BOARD_ID")
    private Matching matching;
    @Column(name="CONTENT", nullable = false)
    private String content;
    @Column(name="WRITE_DATE")
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public MatchingComment(long id, User user, Matching matching, String content, Timestamp createdAt){
        this.id = id;
        this.user = user;
        this.matching = matching;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MatchingComment{" +
                "id=" + id +
                ", user=" + user +
                ", matching=" + matching +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

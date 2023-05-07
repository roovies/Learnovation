package com.kh.learnovation.domain.matching.dto;

import com.kh.learnovation.domain.matching.entity.Matching;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
public class MatchingCommnetDTO {

    private Long id;
    private User user;
    private Matching matching;
    private String content;
    private Timestamp createdAt;

    @Builder
    public MatchingCommnetDTO(long id, User user, Matching matching, String content, Timestamp createdAt){
        this.id = id;
        this.user = user;
        this.matching = matching;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MatchingCommnetDTO{" +
                "id=" + id +
                ", user=" + user +
                ", matching=" + matching +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

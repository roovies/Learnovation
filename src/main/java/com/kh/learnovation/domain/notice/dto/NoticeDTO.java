package com.kh.learnovation.domain.notice.dto;

import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.security.auth.Subject;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class NoticeDTO {

    private long id;
    private long userid;
    private String userName;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int status;
    private String subject;
    @Builder
    public NoticeDTO(long id, User user, String title, String content, Timestamp createdAt, Timestamp updatedAt, int status, String subject){
        this.id = id;
        this.userid = user.getId();
        this.userName = user.getName();
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "id=" + id +
                ", userid=" + userid +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", subject =" + subject +
                '}';
    }
}

package com.kh.learnovation.domain.alarm.dto;

import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AlarmDTO {
    private long id;
    private User user;
    private String content;
    private int status;
    @Builder
    public AlarmDTO(long id, User user, String content, int status){
        this.id = id;
        this.user = user;
        this.content = content;
        this.status = status;
    }

    @Override
    public String toString() {
        return "AlarmDTO{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }
}

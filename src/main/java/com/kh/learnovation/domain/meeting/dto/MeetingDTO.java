package com.kh.learnovation.domain.meeting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class MeetingDTO {

    private long id;
    private String name;
    private Timestamp createdAt;

    @Builder
    public MeetingDTO(long id, String name, Timestamp createdAt){
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MeetingDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

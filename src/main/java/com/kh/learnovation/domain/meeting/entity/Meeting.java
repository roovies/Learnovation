package com.kh.learnovation.domain.meeting.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="CREATE_DATE")
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Meeting(long id, String name, Timestamp createdAt){
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

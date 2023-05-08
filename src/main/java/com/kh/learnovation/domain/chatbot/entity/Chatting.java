package com.kh.learnovation.domain.chatbot.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "chattings")
@DynamicInsert
public class Chatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Chatting(Long id, Room room, Long senderId, String message, Timestamp createdAt){
        this.id = id;
        this.room = room;
        this.senderId = senderId;
        this.message = message;
        this.createdAt = createdAt;
    }
}

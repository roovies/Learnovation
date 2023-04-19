package com.kh.learnovation.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String socialId;

    @Column(length = 50)
    private String socialProvider;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String phoneNumber;

    @Column
    private String profileImage;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    @Column
    private String status;

    @Column
    private Timestamp deletedAt;

    @Builder
    public User(Integer id, String socialId, String socialProvider, String email, String password, String name, String nickname, String phoneNumber, String profileImage, Timestamp createdAt, Timestamp updatedAt, String status, Timestamp deletedAt) {
        this.id = id;
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.deletedAt = deletedAt;
    }
}

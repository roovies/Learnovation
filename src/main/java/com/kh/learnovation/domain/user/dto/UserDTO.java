package com.kh.learnovation.domain.user.dto;

import com.kh.learnovation.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDTO {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String nickname;

    private String phoneNumber;

    private String profileImage;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String status;

    private Timestamp deletedAt;
    private String oauth;

    public User toEntity() {
        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .profileImage(profileImage)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .status(status)
                .deletedAt(deletedAt)
                .oauth(oauth)
                .build();
        return user;
    }

    @Builder
    public UserDTO(Long id, String email, String password, String name, String nickname, String phoneNumber, String profileImage, Timestamp createdAt, Timestamp updatedAt, String status, Timestamp deletedAt, String oauth) {
        this.id = id;
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
        this.oauth = oauth;
    }
}
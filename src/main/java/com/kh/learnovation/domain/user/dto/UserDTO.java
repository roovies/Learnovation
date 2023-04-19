package com.kh.learnovation.domain.user.dto;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String socialId;
    private String socialProvider;
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
    @Builder
    public UserDTO(Long id, String socialId, String socialProvider, String email, String name, String nickname, String phoneNumber, String profileImage, Timestamp createdAt, Timestamp updatedAt, String status, Timestamp deletedAt) {
        this.id = id;
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.email = email;
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

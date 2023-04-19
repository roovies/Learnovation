package com.kh.learnovation.domain.user.dto;

import com.kh.learnovation.domain.user.entity.User;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    private Integer id;
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

    public User toEntity() {
        User user = User.builder()
                .id(id)
                .socialId(socialId)
                .socialProvider(socialProvider)
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
                .build();
        return user;
    }

    @Builder
    public UserDTO(Integer id, String socialId, String socialProvider, String email, String password, String name, String nickname, String phoneNumber, String profileImage, Timestamp createdAt, Timestamp updatedAt, String status, Timestamp deletedAt) {
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

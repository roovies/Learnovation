package com.kh.learnovation.domain.user.entity;

import com.kh.learnovation.domain.user.model.RoleType;
import lombok.*;
import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*    @Column(name = "social_id", length = 100)
    private String socialId;

    @Column(name = "social_provider", length = 50)
    private String socialProvider;*/

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phoneNumber;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @ColumnDefault("'N'")
    private String status;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    /** 승현 User Entity 항목 추가*/
    // @ColumnDefault("user")
    // DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다. // ADMIN, USER
    private String oauth; // kakao, google


    @Builder
    public User(Long id, String email, String password, String name, String nickname, String phoneNumber, String profileImage, Timestamp createdAt, Timestamp updatedAt,
                String status, Timestamp deletedAt, String oauth) {
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

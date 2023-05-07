package com.kh.learnovation.domain.freeboard.entity;


import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import com.kh.learnovation.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "heart")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freeBoard_id")
    private FreeBoardEntity freeBoardEntity;



    public static LikeEntity toSaveEntity(User userEntity, FreeBoardEntity freeBoardEntity) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setUser(userEntity);
        likeEntity.setFreeBoardEntity(freeBoardEntity);
        return likeEntity;
    }
}

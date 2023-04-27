package com.kh.learnovation.domain.freeboard.entity;


import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "like_table")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;
    private String likeWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freeBoard_id")
    private FreeBoardEntity freeBoardEntity;

    public static LikeEntity toSaveEntity(LikeDTO likeDTO, FreeBoardEntity freeBoardEntity) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setLikeWriter(likeDTO.getLikeWriter());
        likeEntity.setFreeBoardEntity(freeBoardEntity);
        return likeEntity;
    }
}

package com.kh.learnovation.domain.freeboard.entity;

import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column
    private String commentContents;

    /* Board:Comment = 1:N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freeBoard_id")
    private FreeBoardEntity freeBoardEntity;

    //------------------------------------------ 여긴 실험 -----------------------------------

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private CommentEntity parentCommentEntity;
//
//
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    private List<CommentEntity> childrenCommentEntity = new ArrayList<>();

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, FreeBoardEntity freeBoardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setFreeBoardEntity(freeBoardEntity);
        return commentEntity;
    }

    public static CommentEntity toUpdateEntity(CommentDTO commentDTO, FreeBoardEntity freeBoardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setFreeBoardEntity(freeBoardEntity);
        return commentEntity;

    }

    public static CommentEntity toDeleteEntity(CommentDTO commentDTO, FreeBoardEntity freeBoardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setFreeBoardEntity(freeBoardEntity);
        return commentEntity;

    }
}

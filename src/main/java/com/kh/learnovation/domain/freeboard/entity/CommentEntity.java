package com.kh.learnovation.domain.freeboard.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String commentContents;
    @Column
    @CreationTimestamp
    private Timestamp createdAt;


    /* Board:Comment = 1:N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freeBoard_id")
    @JsonIgnore // 해당 필드를 직렬화에서 제외시킴
    private FreeBoardEntity freeBoardEntity;



    @Builder
    public CommentEntity(long id, User user, String commentContents, FreeBoardEntity freeBoardEntity, Timestamp createdAt){
        this.id = id;
        this.user = user;
        this.commentContents = commentContents;
        this.freeBoardEntity = freeBoardEntity;
        this.createdAt = createdAt;
    }

//    public static CommentEntity toSaveEntity(CommentDTO commentDTO, FreeBoardEntity freeBoardEntity) {
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
//        commentEntity.setCommentContents(commentDTO.getCommentContents());
//        commentEntity.setFreeBoardEntity(freeBoardEntity);
//        return commentEntity;
//    }
//
//    public static CommentEntity toUpdateEntity(CommentDTO commentDTO, FreeBoardEntity freeBoardEntity) {
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
//        commentEntity.setCommentContents(commentDTO.getCommentContents());
//        commentEntity.setFreeBoardEntity(freeBoardEntity);
//        return commentEntity;
//
//    }
//
//    public static CommentEntity toDeleteEntity(CommentDTO commentDTO, FreeBoardEntity freeBoardEntity) {
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
//        commentEntity.setCommentContents(commentDTO.getCommentContents());
//        commentEntity.setFreeBoardEntity(freeBoardEntity);
//        return commentEntity;
//
//    }
}

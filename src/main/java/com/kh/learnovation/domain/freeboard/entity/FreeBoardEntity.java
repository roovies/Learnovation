package com.kh.learnovation.domain.freeboard.entity;

import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "test_freeBoard_table")
public class FreeBoardEntity extends BaseEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String freeBoardTitle;

    @Column(length = 500)
    private String freeBoardContents;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
    @Column
    private String subject;
    @ColumnDefault("0")
    @Column(nullable = false)
    private int freeBoardHits;

    @Column
    private int status; // 1 or 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;


    @OneToMany(mappedBy = "freeBoardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FreeBoardFileEntity> freeBoardFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "freeBoardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @Builder
    public FreeBoardEntity(long id, String freeBoardTitle, String freeBoardContents, int freeBoardHits, int status
            , User user, Timestamp createdAt, Timestamp updatedAt, String subject
            , List<FreeBoardFileEntity> freeBoardFileEntityList, List<CommentEntity> commentEntityList) {
        this.id = id;
        this.freeBoardTitle = freeBoardTitle;
        this.freeBoardContents = freeBoardContents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.subject = subject;
        this.freeBoardHits = freeBoardHits;
        this.status = status;
        this.user = user;
        this.freeBoardFileEntityList = freeBoardFileEntityList;
        this.commentEntityList = commentEntityList;
    }
}

//    public static FreeBoardEntity toSaveEntity(FreeBoardDTO freeBoardDTO) {
//        FreeBoardEntity freeBoardEntity = new FreeBoardEntity();
////        freeBoardEntity.setUser(freeBoardDTO.getu());
//        freeBoardEntity.setFreeBoardTitle(freeBoardDTO.getFreeBoardTitle());
//        freeBoardEntity.setFreeBoardContents(freeBoardDTO.getFreeBoardContents());
//        freeBoardEntity.setFreeBoardHits(0);
//        freeBoardEntity.setFileAttached(0); // 파일 없음.
//        return freeBoardEntity;
//    }

//    public static FreeBoardEntity toUpdateEntity(FreeBoardDTO freeBoardDTO) {
//        FreeBoardEntity freeBoardEntity = new FreeBoardEntity();
//        freeBoardEntity.setId(freeBoardDTO.getId());
////        freeBoardEntity.setFreeBoardWriter(freeBoardDTO.getFreeBoardWriter());
//        freeBoardEntity.setFreeBoardTitle(freeBoardDTO.getFreeBoardTitle());
//        freeBoardEntity.setFreeBoardContents(freeBoardDTO.getFreeBoardContents());
//        freeBoardEntity.setFreeBoardHits(freeBoardDTO.getFreeBoardHits());
//        return freeBoardEntity;
//    }

//    public static FreeBoardEntity toSaveFileEntity(FreeBoardDTO freeBoardDTO) {
//        FreeBoardEntity freeBoardEntity = new FreeBoardEntity();
////        freeBoardEntity.setFreeBoardWriter(freeBoardDTO.getFreeBoardWriter());
//        freeBoardEntity.setFreeBoardTitle(freeBoardDTO.getFreeBoardTitle());
//        freeBoardEntity.setFreeBoardContents(freeBoardDTO.getFreeBoardContents());
//        freeBoardEntity.setFreeBoardHits(0);
//        freeBoardEntity.setFileAttached(1); // 파일 있음.
//        return freeBoardEntity;
//    }
//}


package com.kh.learnovation.domain.freeboard.entity;

import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "freeBoard_table")
public class FreeBoardEntity extends BaseEntity {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String freeBoardWriter;

    @Column
    private String freeBoardTitle;

    @Column(length = 500)
    private String freeBoardContents;

    @Column
    private int freeBoardHits;

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FreeBoardFileEntity> freeBoardFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static FreeBoardEntity toSaveEntity(FreeBoardDTO freeBoardDTO) {
        FreeBoardEntity freeBoardEntity = new FreeBoardEntity();
        freeBoardEntity.setFreeBoardWriter(freeBoardDTO.getFreeBoardWriter());
        freeBoardEntity.setFreeBoardTitle(freeBoardDTO.getFreeBoardTitle());
        freeBoardEntity.setFreeBoardContents(freeBoardDTO.getFreeBoardContents());
        freeBoardEntity.setFreeBoardHits(0);
        freeBoardEntity.setFileAttached(0); // 파일 없음.
        return freeBoardEntity;
    }

    public static FreeBoardEntity toUpdateEntity(FreeBoardDTO freeBoardDTO) {
        FreeBoardEntity freeBoardEntity = new FreeBoardEntity();
        freeBoardEntity.setId(freeBoardDTO.getId());
        freeBoardEntity.setFreeBoardWriter(freeBoardDTO.getFreeBoardWriter());
        freeBoardEntity.setFreeBoardTitle(freeBoardDTO.getFreeBoardTitle());
        freeBoardEntity.setFreeBoardContents(freeBoardDTO.getFreeBoardContents());
        freeBoardEntity.setFreeBoardHits(freeBoardDTO.getFreeBoardHits());
        return freeBoardEntity;
    }

    public static FreeBoardEntity toSaveFileEntity(FreeBoardDTO freeBoardDTO) {
        FreeBoardEntity freeBoardEntity = new FreeBoardEntity();
        freeBoardEntity.setFreeBoardWriter(freeBoardDTO.getFreeBoardWriter());
        freeBoardEntity.setFreeBoardTitle(freeBoardDTO.getFreeBoardTitle());
        freeBoardEntity.setFreeBoardContents(freeBoardDTO.getFreeBoardContents());
        freeBoardEntity.setFreeBoardHits(0);
        freeBoardEntity.setFileAttached(1); // 파일 있음.
        return freeBoardEntity;
    }
}


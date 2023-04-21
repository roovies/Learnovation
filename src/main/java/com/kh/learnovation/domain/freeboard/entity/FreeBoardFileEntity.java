package com.kh.learnovation.domain.freeboard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "freeBoard_file_table")
public class FreeBoardFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private FreeBoardEntity freeBoardEntity;

    public static FreeBoardFileEntity toBoardFileEntity(FreeBoardEntity freeBoardEntity, String originalFileName, String storedFileName) {
        FreeBoardFileEntity freeBoardFileEntity = new FreeBoardFileEntity();
        freeBoardFileEntity.setOriginalFileName(originalFileName);
        freeBoardFileEntity.setStoredFileName(storedFileName);
        freeBoardFileEntity.setFreeBoardEntity(freeBoardEntity);
        return freeBoardFileEntity;
    }
}

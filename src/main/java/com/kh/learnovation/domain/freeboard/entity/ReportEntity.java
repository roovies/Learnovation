package com.kh.learnovation.domain.freeboard.entity;


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
@Table(name = "report_table")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freeBoard_id")
    private FreeBoardEntity freeBoardEntity;

    @Column
    private String reportTitle;

    @Column(length = 500)
    private String reportContents;


    public static ReportEntity toSaveEntity(User userEntity, FreeBoardEntity freeBoardEntity) {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setUser(userEntity);
        reportEntity.setFreeBoardEntity(freeBoardEntity);
        return reportEntity;
    }
}

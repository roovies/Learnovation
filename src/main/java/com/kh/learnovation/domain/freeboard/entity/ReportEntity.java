package com.kh.learnovation.domain.freeboard.entity;


import com.kh.learnovation.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report")
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

    @Builder
    public ReportEntity(long id, User user, String reportContents, String reportTitle, FreeBoardEntity freeBoardEntity){
        this.id = id;
        this.user = user;
        this.reportContents = reportContents;
        this.freeBoardEntity = freeBoardEntity;
        this.reportTitle = reportTitle;
    }



}

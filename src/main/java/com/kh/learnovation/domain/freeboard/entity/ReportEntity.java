package com.kh.learnovation.domain.freeboard.entity;

import com.kh.learnovation.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String reportReason;
    @Column
    private String reportContent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freeBoard_id")
    private FreeBoardEntity freeBoardEntity;


    @Builder
    public ReportEntity(long id, User user, String reportReason, String reportContent, FreeBoardEntity freeBoardEntity) {
        this.id = id;
        this.user = user;
        this.reportReason = reportReason;
        this.reportContent = reportContent;
        this.freeBoardEntity = freeBoardEntity;

    }



}

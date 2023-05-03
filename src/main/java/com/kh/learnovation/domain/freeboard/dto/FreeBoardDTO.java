package com.kh.learnovation.domain.freeboard.dto;

import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
//@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class FreeBoardDTO {
    private Long id;
    private Long userId;
    private String nickname;
    private String freeBoardTitle;
    private String freeBoardContents;
    private int freeBoardHits;
    private Timestamp freeBoardCreatedTime;
    private Timestamp freeBoardUpdatedTime;
    private String subject;
    private MultipartFile freeBoardFile; // save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)



    public FreeBoardDTO(Long id, String freeBoardTitle, int freeBoardHits, Timestamp freeBoardCreatedTime) {
        this.id = id;
        this.freeBoardTitle = freeBoardTitle;
        this.freeBoardHits = freeBoardHits;
        this.freeBoardCreatedTime = freeBoardCreatedTime;
    }
    @Builder
    public FreeBoardDTO(long id, Long userId, String nickname, String freeBoardTitle, String freeBoardContents, Timestamp freeBoardCreatedTime, Timestamp freeBoardUpdatedTime,  String subject){
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.freeBoardTitle = freeBoardTitle;
        this.freeBoardContents = freeBoardContents;
        this.freeBoardCreatedTime = freeBoardCreatedTime;
        this.freeBoardUpdatedTime = freeBoardUpdatedTime;
//        this.status = status;
        this.subject = subject;
    }



    public static FreeBoardDTO toBoardDTO(FreeBoardEntity freeBoardEntity) {
        FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
        freeBoardDTO.setId(freeBoardEntity.getId());
        freeBoardDTO.setFreeBoardTitle(freeBoardEntity.getFreeBoardTitle());
        freeBoardDTO.setFreeBoardContents(freeBoardEntity.getFreeBoardContents());
        freeBoardDTO.setFreeBoardHits(freeBoardEntity.getFreeBoardHits());
        freeBoardDTO.setUserId(freeBoardEntity.getUser().getId());
        freeBoardDTO.setNickname(freeBoardEntity.getUser().getNickname());
        freeBoardDTO.setFreeBoardCreatedTime(freeBoardEntity.getCreatedTime());
        freeBoardDTO.setFreeBoardUpdatedTime(freeBoardEntity.getUpdatedTime());
        if (freeBoardEntity.getFileAttached() == 0) {
            freeBoardDTO.setFileAttached(freeBoardEntity.getFileAttached()); // 0
        } else {
            freeBoardDTO.setFileAttached(freeBoardEntity.getFileAttached()); // 1
            // 파일 이름을 가져가야 함.
            // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
            // join
            // select * from board_table b, board_file_table bf where b.id=bf.board_id
            // and where b.id=?
            freeBoardDTO.setOriginalFileName(freeBoardEntity.getFreeBoardFileEntityList().get(0).getOriginalFileName());
            freeBoardDTO.setStoredFileName(freeBoardEntity.getFreeBoardFileEntityList().get(0).getStoredFileName());
        }

        return freeBoardDTO;
    }
}
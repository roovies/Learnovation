package com.kh.learnovation.domain.notice.service;

import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;

    @Test
    public void insertTest(){
        Admin admin = Admin.builder().id(2L).build();
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .admin(admin)
                .title("제목입니다")
                .content("내용입니다.")
                .build();
        Notice notice = noticeService.insertNotice(noticeDTO);
        Assertions.assertEquals("제목입니다",notice.getTitle());
    }
}

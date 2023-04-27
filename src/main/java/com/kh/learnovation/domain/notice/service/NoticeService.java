package com.kh.learnovation.domain.notice.service;

import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import com.kh.learnovation.domain.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    NoticeDTO insertNotice(NoticeDTO noticeDTO);

    public NoticeDTO selectOneNotice(Long noticeNo);

    public NoticeDTO updateNotice(NoticeDTO noticeDTO);

    Page<Notice> selectAllNotice(Pageable pageable);

    Page<Notice> selectAllNotice(String keyword, Pageable pageable);
}

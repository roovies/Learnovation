package com.kh.learnovation.domain.notice.service;

import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import com.kh.learnovation.domain.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface NoticeService {
    Notice insertNotice(NoticeDTO noticeDTO);

    public NoticeDTO selectOneNotice(Long noticeNo);

    public Notice updateNotice(NoticeDTO noticeDTO);
}

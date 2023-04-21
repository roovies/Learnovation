package com.kh.learnovation.domain.notice.service;

import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import com.kh.learnovation.domain.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    @Transactional
    public Notice insertNotice(NoticeDTO noticeDTO) {
        Admin admin = Admin.builder().id(noticeDTO.getAdminId()).build();
        Notice notice = Notice.builder()
                .admin(admin)
                .title(noticeDTO.getTitle())
                .content(noticeDTO.getContent())
                .createdAt(noticeDTO.getCreatedAt())
                .updateAt(noticeDTO.getUpdatedAt())
                .build();
        return noticeRepository.save(notice);
    }

    @Override
    @Transactional
    public Notice updateNotice(NoticeDTO noticeDTO) {
        Admin admin = Admin.builder().id(noticeDTO.getAdminId()).build();
        Notice notice = Notice.builder()
                .id(noticeDTO.getId())
                .admin(admin)
                .title(noticeDTO.getTitle())
                .content(noticeDTO.getContent())
                .createdAt(noticeDTO.getCreatedAt())
                .updateAt(noticeDTO.getUpdatedAt())
                .build();
        return noticeRepository.save(notice);
    }

    @Override
    @Transactional
    public NoticeDTO selectOneNotice(Long noticeNo){
        Optional<Notice> noticeOptional = noticeRepository.findById(noticeNo);
        Notice notice = null;
        if(noticeOptional.isPresent()){
            notice = noticeOptional.get();
        }
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .id(notice.getId())
                .admin(notice.getAdmin())
                .title(notice.getTitle())
                .content(notice.getContent())
                .updatedAt(notice.getUpdateAt())
                .build();
        return noticeDTO;
    }
}

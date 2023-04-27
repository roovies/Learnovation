package com.kh.learnovation.domain.notice.service;

import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import com.kh.learnovation.domain.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    @Transactional
    public NoticeDTO insertNotice(NoticeDTO noticeDTO) {
        Admin admin = Admin.builder().id(noticeDTO.getAdminId()).build();
        Notice notice = Notice.builder()
                .admin(admin)
                .title(noticeDTO.getTitle())
                .content(noticeDTO.getContent())
                .createdAt(noticeDTO.getCreatedAt())
                .updatedAt(noticeDTO.getUpdatedAt())
                .status(noticeDTO.getStatus())
                .subject(noticeDTO.getSubject())
                .build();
        notice = noticeRepository.save(notice);
        noticeDTO = NoticeDTO.builder()
                .id(notice.getId())
                .admin(notice.getAdmin())
                .title(notice.getTitle())
                .content(notice.getTitle())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .status(notice.getStatus())
                .subject(notice.getSubject())
                .build();
        return noticeDTO;
    }

    @Override
    @Transactional
    public NoticeDTO updateNotice(NoticeDTO noticeDTO) {
        Admin admin = Admin.builder().id(noticeDTO.getAdminId()).build();
        Notice notice = Notice.builder()
                .id(noticeDTO.getId())
                .admin(admin)
                .title(noticeDTO.getTitle())
                .content(noticeDTO.getContent())
                .createdAt(noticeDTO.getCreatedAt())
                .updatedAt(noticeDTO.getUpdatedAt())
                .status(noticeDTO.getStatus())
                .subject(noticeDTO.getSubject())
                .build();
        notice = noticeRepository.save(notice);
        noticeDTO = NoticeDTO.builder()
                .id(notice.getId())
                .admin(notice.getAdmin())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .status(notice.getStatus())
                .subject(notice.getSubject())
                .build();
        return noticeDTO;
    }

    @Override
    public Page<Notice> selectAllNotice(Pageable pageable) {
        Page<Notice> pNotice = noticeRepository.findAllByStatus(0,pageable);
        return pNotice;
    }

    @Override
    public Page<Notice> selectAllNotice(String keyword, Pageable pageable) {
        Page<Notice> pNotice = noticeRepository.findAllByStatusAndSubjectLikeOrTitleLike(0,keyword,keyword,pageable);
        return pNotice;
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
                .updatedAt(notice.getUpdatedAt())
                .status(notice.getStatus())
                .subject(notice.getSubject())
                .build();
        return noticeDTO;
    }

}

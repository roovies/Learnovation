package com.kh.learnovation.domain.notice.service;

import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import com.kh.learnovation.domain.notice.repository.NoticeRepository;
import com.kh.learnovation.domain.user.entity.User;
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
        User user = User.builder().id(noticeDTO.getUserid()).build();
        Notice notice = Notice.builder()
                .user(user)
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
                .user(notice.getUser())
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
        User user = User.builder().id(noticeDTO.getUserid()).build();
        Notice notice = Notice.builder()
                .id(noticeDTO.getId())
                .user(user)
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
                .user(notice.getUser())
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
    @Transactional
    public Page<Notice> selectAllNotice(Pageable pageable) {
        Page<Notice> pNotice = noticeRepository.findAllByStatus(0,pageable);
        return pNotice;
    }

    @Override
    @Transactional
    public Page<Notice> selectAllNotice(String keyword, Pageable pageable) {
        Page<Notice> pNotice = noticeRepository.findAllByStatusAndTitleLike(0,keyword,pageable);
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
                .user(notice.getUser())
                .title(notice.getTitle())
                .content(notice.getContent())
                .updatedAt(notice.getUpdatedAt())
                .status(notice.getStatus())
                .subject(notice.getSubject())
                .createdAt(notice.getCreatedAt())
                .build();
        return noticeDTO;
    }

}

package com.kh.learnovation.domain.notice.repository;

import com.kh.learnovation.domain.notice.entity.Notice;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}

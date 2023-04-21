package com.kh.learnovation.domain.freeboard.repository;

import com.kh.learnovation.domain.freeboard.entity.FreeBoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardFileRepository extends JpaRepository<FreeBoardFileEntity, Long> {
}

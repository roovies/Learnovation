package com.kh.learnovation.domain.freeboard.repository;

import com.kh.learnovation.domain.freeboard.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}

package com.kh.learnovation.domain.alarm.repository;

import com.kh.learnovation.domain.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM ALARM WHERE user_id = :userid")
    List<Alarm> findByUserid(@Param("userid") long userNo);
}

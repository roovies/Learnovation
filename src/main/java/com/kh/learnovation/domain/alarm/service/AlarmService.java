package com.kh.learnovation.domain.alarm.service;

import com.kh.learnovation.domain.alarm.dto.AlarmDTO;
import com.kh.learnovation.domain.alarm.entity.Alarm;
import com.kh.learnovation.domain.user.dto.UserDTO;

import java.util.List;

public interface AlarmService {
    void insertAlarm(UserDTO userDTO, AlarmDTO alarmDTO);

    List<Alarm> listAlarm(long userNo);

    void deleteAlarm(AlarmDTO alarmDTO);
}

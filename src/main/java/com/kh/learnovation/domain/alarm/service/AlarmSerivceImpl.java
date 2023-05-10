package com.kh.learnovation.domain.alarm.service;

import com.kh.learnovation.domain.alarm.dto.AlarmDTO;
import com.kh.learnovation.domain.alarm.entity.Alarm;
import com.kh.learnovation.domain.alarm.repository.AlarmRepository;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmSerivceImpl implements AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;

    @Override
    public void insertAlarm(UserDTO userDTO, AlarmDTO alarmDTO) {
        User user = User.builder().id(userDTO.getId()).build();
        Alarm alarm = Alarm.builder()
                .content(alarmDTO.getContent())
                .status(alarmDTO.getStatus())
                .user(user)
                .build();
        alarm = alarmRepository.save(alarm);
    }

    @Override
    public List<Alarm> listAlarm(long userNo) {
        List<Alarm> aList = alarmRepository.findByUserid(userNo);
        return aList;
    }

    @Override
    public void deleteAlarm(AlarmDTO alarmDTO) {
        Alarm alarm = Alarm.builder().id(alarmDTO.getId()).build();
        alarmRepository.delete(alarm);
    }
}

package com.kh.learnovation.domain.alarm.service;

import com.kh.learnovation.domain.alarm.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmSerivceImpl implements AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;
}

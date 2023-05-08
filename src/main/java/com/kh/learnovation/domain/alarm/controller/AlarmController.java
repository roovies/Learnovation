package com.kh.learnovation.domain.alarm.controller;

import com.kh.learnovation.domain.alarm.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlarmController {
    @Autowired
    AlarmService alarmService;

    @GetMapping("/socket")
    public String TestView(){
        return "socketTest";
    }
}

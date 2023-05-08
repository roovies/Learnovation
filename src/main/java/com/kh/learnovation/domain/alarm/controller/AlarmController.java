package com.kh.learnovation.domain.alarm.controller;

import com.google.gson.Gson;
import com.kh.learnovation.domain.alarm.dto.AlarmDTO;
import com.kh.learnovation.domain.alarm.entity.Alarm;
import com.kh.learnovation.domain.alarm.service.AlarmService;
import com.kh.learnovation.domain.meeting.entity.Meeting;
import com.kh.learnovation.domain.meeting.service.MeetingService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AlarmController {
    @Autowired
    AlarmService alarmService;
    @Autowired
    MeetingService meetingService;

    @PostMapping("/alarm/insert")
    @ResponseBody
    public String insertAlarm(@RequestParam("boardUserNo") long userNo,@RequestParam("boardTitle") String boardTitle){
        UserDTO userDTO = UserDTO.builder().id(userNo).build();
        AlarmDTO alarmDTO = AlarmDTO.builder().status(0).content(boardTitle +"에 댓글이 작성 되었습니다.").build();
        alarmService.insertAlarm(userDTO, alarmDTO);
        return "success";
    }

    @PostMapping("/alarm/invite")
    @ResponseBody
    public String inviteAlarm(@RequestParam("userNo") long userNo, @RequestParam("groupNo") long groupNo){
        String groupName = meetingService.selectOne(groupNo);
        UserDTO userDTO = UserDTO.builder().id(userNo).build();
        AlarmDTO alarmDTO = AlarmDTO.builder().status(0).content(groupName +"그룹에 초대되었습니다.").build();
        alarmService.insertAlarm(userDTO, alarmDTO);
        return "success";
    }

    @GetMapping("/alarm/list")
    @ResponseBody
    public String alarmList(@RequestParam("userNo") long userNo){
        List<Alarm> aList = alarmService.listAlarm(userNo);
        if(!aList.isEmpty()){
            Gson gson = new Gson();
            return gson.toJson(aList);
        }
        return "empty";
    }

    @GetMapping("/alarm/remove")
    @ResponseBody
    public String deleteAlarm(@RequestParam("alarmNo") long alarmNo){
        AlarmDTO alarmDTO = AlarmDTO.builder().id(alarmNo).build();
        alarmService.deleteAlarm(alarmDTO);
        return "success";
    }
}

package com.kh.learnovation.domain.meeting.controller;

import com.google.gson.Gson;
import com.kh.learnovation.domain.meeting.dto.MeetingChattingDTO;
import com.kh.learnovation.domain.meeting.dto.MeetingDTO;
import com.kh.learnovation.domain.meeting.dto.MeetingMemberDTO;
import com.kh.learnovation.domain.meeting.entity.Meeting;
import com.kh.learnovation.domain.meeting.entity.MeetingChatting;
import com.kh.learnovation.domain.meeting.entity.MeetingMember;
import com.kh.learnovation.domain.meeting.entity.MeetingMemberPk;
import com.kh.learnovation.domain.meeting.service.MeetingService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    @Autowired
    private UserService userService;

    @PostMapping("/meeting/create")
    @ResponseBody
    public String MeetingCreate(@RequestParam("groupName") String groupName, @RequestParam("userNo") long userId){
        if(groupName.equals("")){
            return "fail";
        }
        MeetingDTO meetingDTO = MeetingDTO.builder().name(groupName).build();
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        UserDTO curUser = null;
        if (userDTO.isPresent()){
            if(userDTO.get().getId() == userId){
                curUser = userDTO.get();
            }else{
                return "fail";
            }
        }else{
            return "fail";
        }
        meetingDTO = meetingService.createMeeting(meetingDTO);
        MeetingMemberDTO meetingMemberDTO = meetingService.inviteMeeting(meetingDTO, curUser);
        return "success";
    }

    @GetMapping("/meeting/list")
    @ResponseBody
    public String MeetingList(@RequestParam("userNo") long userNo){
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            if(userDTO.get().getId() == userNo){
                List<Meeting> mList =  meetingService.MeetingList(userNo);
                Gson gson = new Gson();
                return gson.toJson(mList);
            }
        }
        return "fail";
    }

    @GetMapping("/meeting/exit")
    @ResponseBody
    public String meetingExit(@RequestParam("groupNo") long groupNo){
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        UserDTO curUser = null;
        if (userDTO.isPresent()){
            curUser = userDTO.get();
        }else{
            return "fail";
        }
        meetingService.exit(groupNo, curUser);
        int totalCount = meetingService.getTotalCount(groupNo);
        if(totalCount == 0){
            meetingService.delete(groupNo);
        }
        return "success";
    }

    @PostMapping("/meeting/invite")
    @ResponseBody
    public String meetingInvite(@RequestParam("groupNo") long groupNo, @RequestParam("userNo") long userNo){
        List<Meeting> mList = meetingService.MeetingList(userNo);
        for(Meeting meeting : mList){
            if(meeting.getId() == groupNo){
                return "중복";
            }
        }
        UserDTO userDTO = UserDTO.builder().id(userNo).build();
        MeetingDTO meetingDTO = MeetingDTO.builder().id(groupNo).build();
        meetingService.inviteMeeting(meetingDTO, userDTO);
        return "success";
    }
    @GetMapping("/meeting/chat/room")
    @ResponseBody
    public String openChattingRoom(@RequestParam("meetingNo") long meetingNo){
        List<MeetingChatting> mCList = meetingService.chattingList(meetingNo);
        if(mCList.size() < 1){
            return "empty";
        }
        Gson gson = new Gson();
        return gson.toJson(mCList);
    }

    @PostMapping("/meeting/chat/insert")
    @ResponseBody
    public String insertChat(@RequestParam("userNo") long userNo
            , @RequestParam("groupNo") long meetingNo
            , @RequestParam("content") String content){
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if(userDTO.isPresent()){
            if(userDTO.get().getId() == userNo){
                UserDTO curUser = userDTO.get();
                MeetingDTO meetingDTO = MeetingDTO.builder().id(meetingNo).build();
                meetingService.insertChat(curUser, meetingDTO, content);
                return "success";
            }
        }
        return "fail";
    }

    @GetMapping("/meeting/member/list")
    @ResponseBody
    public String meetingMemberList(@RequestParam("meetingNo") long meetingNo){
        List<MeetingMember> mList = meetingService.meetingMemberList(meetingNo);
        Gson gson = new Gson();
        return gson.toJson(mList);
    }
}

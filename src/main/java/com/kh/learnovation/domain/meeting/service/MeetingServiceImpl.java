package com.kh.learnovation.domain.meeting.service;

import com.kh.learnovation.domain.meeting.dto.MeetingChattingDTO;
import com.kh.learnovation.domain.meeting.dto.MeetingDTO;
import com.kh.learnovation.domain.meeting.dto.MeetingMemberDTO;
import com.kh.learnovation.domain.meeting.entity.Meeting;
import com.kh.learnovation.domain.meeting.entity.MeetingChatting;
import com.kh.learnovation.domain.meeting.entity.MeetingMember;
import com.kh.learnovation.domain.meeting.entity.MeetingMemberPk;
import com.kh.learnovation.domain.meeting.repository.MeetingChattingRepository;
import com.kh.learnovation.domain.meeting.repository.MeetingMemberRepository;
import com.kh.learnovation.domain.meeting.repository.MeetingRepository;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class MeetingServiceImpl implements MeetingService{

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    MeetingMemberRepository meetingMemberRepository;
    @Autowired
    MeetingChattingRepository meetingChattingRepository;
    @Override
    @Transactional
    public MeetingDTO createMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = Meeting.builder().name(meetingDTO.getName()).build();
        Meeting resultMeeting = meetingRepository.save(meeting);
        MeetingDTO resultDTO = MeetingDTO.builder().id(resultMeeting.getId()).name(resultMeeting.getName()).createdAt(resultMeeting.getCreatedAt()).build();
        return resultDTO;
    }

    @Override
    public MeetingMemberDTO inviteMeeting(MeetingDTO meetingDTO, UserDTO userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .nickname(userDTO.getNickname())
                .build();
        Meeting meeting = Meeting.builder().createdAt(meetingDTO.getCreatedAt()).id(meetingDTO.getId()).name(meetingDTO.getName()).build();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        MeetingMemberPk meetingMemberPk = MeetingMemberPk.builder().user(user).meeting(meeting).build();
        MeetingMember meetingMember = MeetingMember.builder().meetingMemberPk(meetingMemberPk).lastView(curTime).build();
        MeetingMember resultMeetingMember = meetingMemberRepository.save(meetingMember);
        MeetingMemberDTO meetingMemberDTO = MeetingMemberDTO.builder()
                .meeting(resultMeetingMember.getMeetingMemberPk().getMeeting())
                .user(resultMeetingMember.getMeetingMemberPk().getUser())
                .lastView(resultMeetingMember.getLastView())
                .build();
        return meetingMemberDTO;
    }

    @Override
    public List<Meeting> MeetingList(long userNo) {
        List<Meeting> mList = meetingRepository.findAllByUserNo(userNo);
        return mList;
    }

    @Override
    @Transactional
    public void exit(long groupNo, UserDTO curUser) {
        Meeting meeting = Meeting.builder().id(groupNo).build();
        User user = User.builder().id(curUser.getId()).build();
        MeetingMemberPk meetingMemberPk = MeetingMemberPk.builder().meeting(meeting).user(user).build();
        MeetingMember meetingMember = MeetingMember.builder().meetingMemberPk(meetingMemberPk).build();
        meetingMemberRepository.delete(meetingMember);
    }

    @Override
    public int getTotalCount(long groupNo) {
        Meeting meeting = Meeting.builder().id(groupNo).build();
        int result = meetingMemberRepository.countByMeeting(groupNo);
        return result;
    }

    @Override
    public void delete(long groupNo) {
        Meeting meeting = Meeting.builder().id(groupNo).build();
        meetingRepository.delete(meeting);
    }

    @Override
    public String selectOne(long groupNo) {
        Optional<Meeting> oMeeting = meetingRepository.findById(groupNo);
        if(oMeeting.isPresent()){
            return oMeeting.get().getName();
        }
        return "fail";
    }

    @Override
    public List<MeetingChatting> chattingList(long meetingNo) {
        Meeting meeting = Meeting.builder().id(meetingNo).build();
        List<MeetingChatting> mCList = meetingChattingRepository.findByMeetingOrderByCreateAtAsc(meeting);
        return mCList;
    }

    @Override
    public void insertChat(UserDTO curUser, MeetingDTO meetingDTO, String content) {
        User user = User.builder().id(curUser.getId()).nickname(curUser.getNickname()).build();
        Meeting meeting = Meeting.builder().id(meetingDTO.getId()).build();
        MeetingChattingDTO meetingChattingDTO = MeetingChattingDTO.builder()
                .user(user)
                .meeting(meeting)
                .Content(content)
                .build();
        MeetingChatting meetingChatting = MeetingChatting.builder()
                .meeting(meetingChattingDTO.getMeeting())
                .user(meetingChattingDTO.getUser())
                .content(meetingChattingDTO.getContent())
                .build();
        meetingChatting = meetingChattingRepository.save(meetingChatting);
    }

    @Override
    public List<MeetingMember> meetingMemberList(long meetingNo) {
        List<MeetingMember> mList =  meetingMemberRepository.findByMeetingId(meetingNo);
        return mList;
    }


}

package com.kh.learnovation.domain.meeting.service;

import com.kh.learnovation.domain.meeting.dto.MeetingDTO;
import com.kh.learnovation.domain.meeting.dto.MeetingMemberDTO;
import com.kh.learnovation.domain.meeting.entity.Meeting;
import com.kh.learnovation.domain.user.dto.UserDTO;

import java.util.List;

public interface MeetingService {
    MeetingDTO createMeeting(MeetingDTO meetingDTO);

    MeetingMemberDTO inviteMeeting(MeetingDTO meetingDTO, UserDTO userDTo);

    List<Meeting> MeetingList(long userNo);


    void exit(long groupNo, UserDTO curUser);

    int getTotalCount(long groupNo);

    void delete(long groupNo);

}

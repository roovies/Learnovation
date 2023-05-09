package com.kh.learnovation.domain.freeboard.controlller;


import com.kh.learnovation.domain.freeboard.dto.ReportDTO;
import com.kh.learnovation.domain.freeboard.service.CommentService;
import com.kh.learnovation.domain.freeboard.service.FreeBoardService;
import com.kh.learnovation.domain.freeboard.service.LikeService;
import com.kh.learnovation.domain.freeboard.service.ReportService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final FreeBoardService freeBoardService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final UserService userService;
    private final ReportService reportService;
    @GetMapping("/insert")
    @ResponseBody
    public String reportInsert(
             @RequestParam("reportReason") String reportReason
            , @RequestParam("reportContent") String reportContent
            , @RequestParam("curUserNo") Long curUserNo
            , @RequestParam("freeBoardNo") Long freeBoardNo){

        Optional<UserDTO> optionalUserDTO = userService.getCurrentUser();
        if (optionalUserDTO.isPresent()){
            UserDTO userDTO = optionalUserDTO.get();
            ReportDTO reportDTO = ReportDTO.builder().userId(userDTO.getId()).nickname(userDTO.getNickname())
                    .reportReason(reportReason).reportContent(reportContent).freeBoardId(freeBoardNo).build();
            reportService.insertReport(reportDTO);
            return "success";
        }
        return "faild";
    }
}

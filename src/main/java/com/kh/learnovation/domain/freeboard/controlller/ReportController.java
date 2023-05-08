package com.kh.learnovation.domain.freeboard.controlller;


import com.kh.learnovation.domain.freeboard.dto.ReportDTO;
import com.kh.learnovation.domain.freeboard.service.CommentService;
import com.kh.learnovation.domain.freeboard.service.FreeBoardService;
import com.kh.learnovation.domain.freeboard.service.LikeService;
import com.kh.learnovation.domain.freeboard.service.ReportService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final FreeBoardService freeBoardService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final UserService userService;
    private final ReportService reportService;
    @PostMapping("/write")
    public ModelAndView reportwrite(ModelAndView mv
            , @RequestParam("reportTitle") String reportTitle
            , @RequestParam("reportContents") String reportContents
            , @RequestParam("freeBoardId") Long freeBoardId){
        if(reportTitle.equals("") || reportContents.equals("")) {
            mv.setViewName("redirect:/freeBoard/detail");
            return mv;
        }
        UserDTO userDTO = userService.getCurrentUser().get();
        ReportDTO reportDTO = ReportDTO.builder().userId(userDTO.getId()).nickname(userDTO.getNickname())
                .reportTitle(reportTitle).reportContent(reportContents).freeBoardId(freeBoardId).build();
        Long reportId = reportService.insertReport(reportDTO);
                return mv;
    }


}

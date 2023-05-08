package com.kh.learnovation.domain.freeboard.controlller;

import com.google.gson.Gson;
import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.entity.CommentEntity;
import com.kh.learnovation.domain.freeboard.entity.FreeBoardEntity;
import com.kh.learnovation.domain.freeboard.service.CommentService;
import com.kh.learnovation.domain.freeboard.service.CommentServiceImpl;
import com.kh.learnovation.domain.matching.dto.MatchingCommnetDTO;
import com.kh.learnovation.domain.matching.entity.Matching;
import com.kh.learnovation.domain.matching.entity.MatchingComment;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    @PostMapping("/insert")
    @ResponseBody
    public String write(
            @RequestParam("content") String commentContents
            ,@RequestParam("freeBoardNo") Long freeBoardId) {
        FreeBoardEntity freeBoardEntity = FreeBoardEntity.builder().id(freeBoardId).build();
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            UserDTO DTO = userDTO.get();
            User user = User.builder()
                    .id(DTO.getId())
                    .build();
            CommentDTO commentDTO = CommentDTO.builder()
                    .freeBoardEntity(freeBoardEntity)
                    .commentContents(commentContents)
                    .user(user)
                    .build();
            commentService.insertComment(commentDTO);
            return "sucess";
        }
        return "fail";
    }

    @PostMapping("/comment/list")
    @ResponseBody
    public String CommentList(@RequestParam("freeBoardId") Long freeBoardId){
        FreeBoardEntity freeBoardEntity = FreeBoardEntity.builder().id(freeBoardId).build();
        List<CommentEntity> cList = commentService.selectList(freeBoardEntity);
        Gson gson = new Gson();
        return gson.toJson(cList);
    }





    @PostMapping("/modify")
    public String modify(@RequestParam("id") Long id
            ,@RequestParam("userId") Long userId
            ,@RequestParam("commentContents") String content) {
//        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            if(userId != userDTO.get().getId()){
                return "noPermission";
            }
        }else{
            return "noPermission";
        }
        CommentDTO commentDTO = CommentDTO.builder().id(id).commentContents(content).build();
        commentService.updateComment(commentDTO);
        return "success";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("userId") Long userId){
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            if(userId != userDTO.get().getId()){
                return "noPermission";
            }
        }else{
            return "noPermission";
        }
        CommentDTO commentDTO= CommentDTO.builder().id(id).build();
        commentService.deleteComment(commentDTO);
        return "success";
    }


}
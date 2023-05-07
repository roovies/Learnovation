package com.kh.learnovation.domain.freeboard.controlller;

import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.service.CommentService;
import com.kh.learnovation.domain.freeboard.service.CommentServiceImpl;
import com.kh.learnovation.domain.user.dto.UserDTO;
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
    @PostMapping("/write")
    public ResponseEntity write(
            @RequestParam("commentContents") String commentContents
            ,@RequestParam("freeBoardId") Long freeBoardId) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Optional<UserDTO> optionalUserDTO =userService.getCurrentUser();
        if (optionalUserDTO.isPresent()){
            UserDTO userDTO = optionalUserDTO.get();
            System.out.println(userDTO.toString());
            CommentDTO commentDTO = CommentDTO.builder().userId(userDTO.getId()).nickname(userDTO.getNickname())
                    .email(userDTO.getEmail()).commentContents(commentContents).freeBoardId(freeBoardId)
                    .commentCreatedTime(ts).build();
            commentDTO = commentService.write(commentDTO);
            Long saveResult=commentDTO.getId();
            if (saveResult != null) {
                List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getFreeBoardId());
                return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
            }

        }else {
            return new ResponseEntity<>("redirect:/user/loginForm", HttpStatus.NOT_FOUND);
        }
        }



    @PostMapping("/update")
    public ResponseEntity update(@RequestParam("id") Long id
            ,@RequestParam("freeBoardId") Long freeBoardId
            ,@RequestParam("commentContents") String content) {
        System.out.println(id);
        System.out.println(freeBoardId);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Optional<UserDTO> optionalUserDTO = userService.getCurrentUser();
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setFreeBoardId(freeBoardId);
        commentDTO.setCommentContents(content);
        System.out.println(commentDTO.getFreeBoardId());
        System.out.println(commentDTO.toString());
        if (optionalUserDTO.isPresent()) {
                commentDTO.setUserId(optionalUserDTO.get().getId());
                commentDTO.setCommentCreatedTime(ts);
                commentDTO = commentService.update(commentDTO);
                return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("실패", HttpStatus.OK);
        }
    }
//
//    @PostMapping("/delete")
//    public void delete(@ModelAttribute CommentDTO commentDTO, Model model){
//        commentService.delete(commentDTO);
//    }

}
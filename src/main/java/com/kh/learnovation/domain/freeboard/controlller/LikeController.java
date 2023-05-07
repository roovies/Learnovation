package com.kh.learnovation.domain.freeboard.controlller;


import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import com.kh.learnovation.domain.freeboard.service.LikeService;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;


    @PostMapping("/like")
    public ResponseEntity check(@ModelAttribute LikeDTO likeDTO){
        Optional<UserDTO> optionalUserDTO = userService.getCurrentUser();
        if (optionalUserDTO.isPresent()){
            UserDTO userDTO =optionalUserDTO.get();
            likeDTO.setUserId(userDTO.getId());
            int result =likeService.check(likeDTO);
            if(result==0){
                likeService.save(likeDTO);
                return new ResponseEntity<>("liked", HttpStatus.OK);
            }else {
                likeService.delete(likeDTO);
                int count = 0;
                return new ResponseEntity<>("unliked", HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>("실패", HttpStatus.OK);

        }
    }


}





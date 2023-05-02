package com.kh.learnovation.domain.freeboard.controlller;


import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import com.kh.learnovation.domain.freeboard.service.LikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeServiceImpl likeService;


    @PostMapping("/check")
    public ResponseEntity check(@ModelAttribute LikeDTO likeDTO){
        int result =likeService.check(likeDTO);
        if(result==0){
            likeService.save(likeDTO);
            int count = 1;
            return new ResponseEntity<>(count, HttpStatus.OK);
        }else {
            likeService.delete(likeDTO);
            int count = 0;
            return new ResponseEntity<>(count, HttpStatus.OK);
        }
    }


}





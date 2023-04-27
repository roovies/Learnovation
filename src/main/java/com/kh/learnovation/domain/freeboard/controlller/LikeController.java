package com.kh.learnovation.domain.freeboard.controlller;


import com.kh.learnovation.domain.freeboard.dto.LikeDTO;
import com.kh.learnovation.domain.freeboard.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;


//    @PostMapping
//    public ResponseEntity save(@ModelAttribute LikeDTO likeDTO) {
//        likeService.save(likeDTO);
//        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
//    }
//
//    @DeleteMapping
//    public ResponseEntity delete(@RequestBody @Valid HeartRequestDTO heartRequestDTO) {
//        heartService.delete(heartRequestDTO);
//        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
//    }

}



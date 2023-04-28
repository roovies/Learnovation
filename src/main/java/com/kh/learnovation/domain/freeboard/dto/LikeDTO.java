package com.kh.learnovation.domain.freeboard.dto;


import com.kh.learnovation.domain.freeboard.entity.LikeEntity;
import com.kh.learnovation.domain.user.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class LikeDTO {
    private Long id;
    private Long userId;
    private Long freeBoardId;

    public static LikeDTO toLikeDTO(LikeEntity likeEntity, Long freeBoardId) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(likeEntity.getId());
        likeDTO.setUserId(likeEntity.getUser().getId());
        likeDTO.setFreeBoardId(freeBoardId);
        return likeDTO;
    }

}

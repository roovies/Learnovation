package com.kh.learnovation.domain.freeboard.controlller;


import com.google.gson.JsonObject;
import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.freeboard.service.CommentServiceImpl;
import com.kh.learnovation.domain.freeboard.service.FreeBoardService;
import com.kh.learnovation.domain.freeboard.service.FreeBoardServiceImpl;
import com.kh.learnovation.domain.freeboard.service.LikeServiceImpl;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freeBoard")
public class FreeBoardController {
    private final FreeBoardService freeBoardService;
    private final CommentServiceImpl commentService;
    private final LikeServiceImpl likeService;




    @GetMapping("/write")
    public ModelAndView freeBoardWriteView(ModelAndView mv){
        mv.addObject("random", UUID.randomUUID()).setViewName("freeBoard/write");
        return mv;
    }


    @PostMapping("/write")
    public ModelAndView save(ModelAndView mv
            , @RequestParam("freeBoardContents") String freeBoardContents
            , @RequestParam("freeBoardTitle") String freeBoardTitle
            , @RequestParam("user") UserDTO userDTO
            , @RequestParam("id") String id) throws IOException {
        if(freeBoardContents.equals("") || freeBoardTitle.equals("")){
            mv.setViewName("redirect:/freeBoard/list");
            return mv;
        }
        // System.currentTimeMillis()는 현재 시간을 1970년 1월 1일 00:00:00 GMT로부터 경과한 시간을 밀리초 단위로 반환합니다. 이 값을 Timestamp 생성자에 전달하여 현재 시간을 나타내는 Timestamp 객체를 생성합니다.
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        // replaceAll(":", "")은 문자열 내의 ":" 문자를 빈 문자열로 대체하는 메서드입니다.  ts.toString()이 "12:34:56"을 반환하고 있다면, time 변수에는 "123456"이 저장됩니다.
        String time = ts.toString().replaceAll(":", "");
        // split("\"")는 가져온 문자열을 "를 구분자로 사용하여 분할하는 메서드입니다. 이 때, \"는 이스케이프 문자를 사용하여 "를 문자열 내에서 정규 표현식으로 표현한 것입니다. 따라서 "를 구분자로 사용하여 문자열을 분할하게 됩니다.
        String[] sList = freeBoardContents.split("\"");
        List<String> fileList = new ArrayList<String>();
        // 주어진 코드는 문자열 aa가 /로 시작하는지 확인하고, 시작하는 경우 fileList에 aa의 일부분인 인덱스 7부터 끝까지의 문자열을 추가하는 기능을 수행합니다.
        for(String aa : sList){
            if(aa.startsWith("/")){
                fileList.add(aa.substring(7));
            }
        }
        File diretory = new File("C:\\img\\freeBoard\\"+ time);
        if(!diretory.exists()){
            diretory.mkdirs();
        }
        File file = new File("C:\\img\\" + id);
        if(file.exists()){ //파일존재여부확인
            if(file.isDirectory()){ //파일이 디렉토리인지 확인
                File[] files = file.listFiles();
                for(int i = 0; i < files.length; i++){
                    for(String fileName : fileList) {
                        if ((id + "/" + files[i].getName()).equals(fileName)) {
                            files[i].renameTo(new File("C:\\img\\freeBoard\\" + time + "\\" + files[i].getName()));
                        }
                    }
                    if (files[i].delete()) {
                        // 폴더 안 파일 삭제 성공시
                    } else {
                        // 삭제 실패시
                    }
                }
            }
            if(file.delete()){
                // 폴더 삭제시
            }else{
                // 폴더 삭제 실패시
            }
        }else{
            // 임시 폴더가 없을 시
        }
        freeBoardContents = freeBoardContents.replaceAll(id, "freeBoard/" + time);
        /*String[] trueContent = content.split("<");
        StringBuilder sb = new StringBuilder("");
        for(String tContent : trueContent){
            if(tContent.contains(">")){
                if(!(tContent.substring(tContent.indexOf(">") + 1).equals(""))){
                    sb.append((tContent.substring(tContent.indexOf(">") + 1)));
                }
            }
        }
        System.out.println(sb);*/
        String subject = freeBoardContents.replaceAll("<[^>]*>?","");
        //NoticeDTO noticeDTO = NoticeDTO.builder().title(title).content(content).admin(admin).status(0).subject(subject).createdAt(ts).build();
        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder().freeBoardTitle(freeBoardTitle).freeBoardContents(freeBoardContents)
                .userId(userDTO.getId()).nickname(userDTO.getNickname()).subject(subject).freeBoardCreatedTime(ts).build();
        long freeBoardNo = freeBoardService.insertFreeBoard(freeBoardDTO).getId();
        mv.setViewName("redirect:/freeBoard/detail?freeBoardNo=" + freeBoardNo);
        return mv;
    }

    @PostMapping(value = "/ImgFileUpload", produces = "application/json; charset=utf8")
    @ResponseBody
    public String freeBoardImgUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") String id, HttpServletRequest request){
        JsonObject jsonObject = new JsonObject();
        LocalDate now = LocalDate.now();
        String savePath = "C:\\img\\" + id;
        //폴더가 없을 경우 자동으로 만들어주기 위한 코드(폴더가 있는 경우 동작 안함)
        File folder = new File(savePath);
        if(!folder.exists()) {
            folder.mkdirs();
        }
        try {
            //실제 파일 저장
            String filename = UUID.randomUUID() + multipartFile.getOriginalFilename();
            String filePath = savePath + "\\" + filename;
            File file = new File(filePath);
            multipartFile.transferTo(file);
            filePath = "/image/" + id +"/"+ filename;
            jsonObject.addProperty("src", filePath); // contextroot + resources + 저장할 내부 폴더명
            jsonObject.addProperty("responseCode", "success");
        } catch (Exception e) {
        }
        String a = jsonObject.toString();
        return a;
    }


    @GetMapping("/list")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<FreeBoardDTO> freeBoardDTOList = freeBoardService.findAll();
        model.addAttribute("freeBoardDTOList", freeBoardDTOList);
        return "/freeBoard/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        freeBoardService.updateHits(id);
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        /* 댓글 목록 가져오기 */
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        int result = likeService.likeCheck(id);
        model.addAttribute("result", result);
        model.addAttribute("commentList", commentDTOList);
        model.addAttribute("freeBoard", freeBoardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "/freeBoard/FreeDetail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        model.addAttribute("freeBoardUpdate", freeBoardDTO);
        return "/freeBoard/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute FreeBoardDTO freeBoardDTO, Model model) {
        FreeBoardDTO freeBoard = freeBoardService.update(freeBoardDTO);
        model.addAttribute("freeBoard", freeBoard);
        return "/freeBoard/detail";
//        return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        freeBoardService.delete(id);
        return "redirect:/freeBoard/";
    }

    // /board/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model, String searchKeyword) {

        Page<FreeBoardDTO> list = null;
        if (searchKeyword == null){
            list = freeBoardService.freeBoardList(pageable);
        }else{
            list = freeBoardService.freeBoardSearchList(pageable, searchKeyword);
        }
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < list.getTotalPages()) ? startPage + blockLimit - 1 : list.getTotalPages();
        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개
        model.addAttribute("list", list);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/freeBoard/paging";
    }

//    @PostMapping("/like")
//    public @ResponseBody int like(Long boardId, Long memberId) {
//        int result = bs.saveLike(boardId,memberId);
//        return result;
//    }

}

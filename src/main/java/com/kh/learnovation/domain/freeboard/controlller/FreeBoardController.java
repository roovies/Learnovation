package com.kh.learnovation.domain.freeboard.controlller;


import com.google.gson.JsonObject;
import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.freeboard.dto.CommentDTO;
import com.kh.learnovation.domain.freeboard.dto.FreeBoardDTO;
import com.kh.learnovation.domain.freeboard.service.*;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
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
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freeBoard")
public class FreeBoardController {
    private final FreeBoardService freeBoardService;
    private final CommentService commentService;
    private final LikeServiceImpl likeService;
    private final UserService userService;




    @GetMapping("/write")
    public ModelAndView freeBoardWriteView(ModelAndView mv){
        Optional<UserDTO> userDTO =userService.getCurrentUser();
        if (userDTO.isPresent()){
            mv.addObject("random", UUID.randomUUID()).setViewName("freeBoard/write");
        }else {
            mv.setViewName("user/loginForm");
        }
        return mv;
    }


    @PostMapping("/write")
    public ModelAndView save(ModelAndView mv
            , @RequestParam("freeBoardContents") String freeBoardContents
            , @RequestParam("freeBoardTitle") String freeBoardTitle
            , @RequestParam("email") String email
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
        File diretory = new File("C:\\img\\freeBoard\\"+ freeBoardTitle + time);
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
                            files[i].renameTo(new File("C:\\img\\freeBoard\\" + freeBoardTitle + time + "\\" + files[i].getName()));
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
        freeBoardContents = freeBoardContents.replaceAll(id, "freeBoard/" + freeBoardTitle + time);
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
        UserDTO userDTO=userService.getCurrentUser().get();
        String userEmail =userDTO.getEmail();
        System.out.println(userEmail);
        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder().freeBoardTitle(freeBoardTitle).freeBoardContents(freeBoardContents)
                .userId(userDTO.getId()).nickname(userDTO.getNickname()).status(0).subject(subject).freeBoardCreatedTime(ts).build();
        Long freeBoardId = freeBoardService.insertFreeBoard(freeBoardDTO).getId();
        mv.addObject("userEmail", userEmail).setViewName("redirect:/freeBoard/detail/" + freeBoardId);
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


//    @GetMapping("/list")
//    public String findAll(Model model) {
//        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
//        List<FreeBoardDTO> freeBoardDTOList = freeBoardService.findAll();
//        model.addAttribute("freeBoardDTOList", freeBoardDTOList);
//        return "/freeBoard/list";
//    }

    @GetMapping("/detail/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @RequestParam (value="searchKeyword", required = false, defaultValue = "") String searchKeyword,
                           @PageableDefault(page=1) Pageable pageable) {
        /*
            해당 게시글의 조회수를 하나 올리고
            게시글 데이터를 가져와서 detail.html에 출력
         */
        freeBoardService.updateHits(id);
        Long countLike = freeBoardService.countLikesByFreeBoardId(id);
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        /* 댓글 목록 가져오기 */
        List<CommentDTO> commentDTOList = commentService.findAll(id);
//        int result = likeService.likeCheck(id);
        freeBoardDTO.getEmail();
//        model.addAttribute("result", result);
        model.addAttribute("countLike", countLike);
        model.addAttribute("commentDTOList", commentDTOList);
        model.addAttribute("freeBoard", freeBoardDTO);
        model.addAttribute("keyword", searchKeyword);
        model.addAttribute("page", pageable.getPageNumber());
        return "/freeBoard/detail";
    }

    @GetMapping("/modify/{id}")
    public ModelAndView updateForm(ModelAndView mv, @PathVariable Long id) {
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        mv.addObject("freeBoard", freeBoardDTO).addObject("random", UUID.randomUUID()).setViewName("freeBoard/modify");
        return mv;
    }

    @PostMapping("/modify")
    public ModelAndView update (ModelAndView mv
            , @RequestParam("freeBoardContents") String freeBoardContents
            , @RequestParam("freeBoardTitle") String freeBoardTitle
            , @RequestParam("id") String id
            , @RequestParam("userId") String userId
            , @RequestParam("freeBoardId") String freeBoardId
            , @RequestParam("createTime") Timestamp ts
            , @RequestParam("originalTitle") String originalTitle) {
        if(freeBoardContents.equals("") || freeBoardTitle.equals("")){
            mv.setViewName("redirect:/freeBoard/list");
            return mv;
        }
        String time = ts.toString().replaceAll(":", "");
        if(!freeBoardTitle.equals(originalTitle)){
            File originDirectory = new File("C:\\img\\freeBoard\\" + originalTitle + time);
            File newDirectory = new File("C:\\img\\freeBoard\\" + freeBoardTitle + time);
            File[] originFiles = originDirectory.listFiles();
            if(originFiles != null){
                for(int i = 0; i < originFiles.length; i++){
                    originFiles[i].renameTo(new File("C:\\img\\freeBoard\\" + freeBoardTitle + time + "\\" + originFiles[i].getName()));
                }
                originDirectory.renameTo(newDirectory);
            }
        }
        // <p><img src="/image/notice/와우/efc877df-84ff-4495-9cad-7189dfe92d42플차.png"><img src="/image/notice/와우/f4e42ed1-ec45-4a05-a562-ab9fb06666be제목 없음.png"><br></p>
        // 썸머노트 글 작성시 내용 예시
        // "로 시작하는거로 짜름

        String[] sList = freeBoardContents.split("\"");
        List<String> fileList = new ArrayList<String>();
        for(String aa : sList){
            // /로 시작하는거 찾음
            if(aa.startsWith("/")){
                // /image/부분 공통이라 짜름
                fileList.add(aa.substring(7));
            }
        }
        // c 아래 img 아래 noitce아래 제목으로 파일 객체 생성
        File diretory = new File("C:\\img\\freeBoard\\" + freeBoardTitle + time);
        if(!diretory.exists()){
            // 객체가 존재하지않으면 해당경로에 폴더 생성
            diretory.mkdirs();
        }
        // 해당 폴더에 이미지 파일들 파일 배열로 저장
        File[] originalFiles = diretory.listFiles();
        // 해당 폴더에 파일에서 수정된 게시글에서 지워진 이미지 판단 할 배열
        int[] valid = new int[originalFiles.length];
        // 기존 폴더에서 수정된 게시글과 비교해 삭제되지 않은거 배열에 저장
        for(int i = 0; i < originalFiles.length; i++){
            for(String fileName : fileList){
                if(("freeBoard/" + freeBoardTitle + time + "/" + originalFiles[i].getName()).equals(fileName)){
                    valid[i] = 1;
                }
            }
        }
        // 수정된 게시글에서 삭제된 이미지 삭제
        for (int i = 0; i < originalFiles.length; i++){
            if(valid[i] != 1){
                if(originalFiles[i].delete()){
                    //원 파일 삭제 성공
                }else{
                    //원 파일 삭제 실패
                }
            }
        }
        // 임시 이미지 파일 저장된 폴더 파일 객체에 담음
        File file = new File("C:\\img\\" + id);
        // 임시 이미지 파일 담은 폴더가 존재 여부 확인
        if(file.exists()){ //파일존재여부확인
            // 폴더가 폴더인지 확인
            if(file.isDirectory()){ //파일이 디렉토리인지 확인
                // 폴더에 파일들 담은 배열 생성
                File[] files = file.listFiles();
                // 임시 파일중 게시글 내용에 포함된거 제목으로 된 폴더로 이동 나머지는 삭제
                for(int i = 0; i < files.length; i++){
                    for(String fileName : fileList) {
                        if ((id + "/" + files[i].getName()).equals(fileName)) {
                            files[i].renameTo(new File("C:\\img\\freeBoard\\" + freeBoardTitle + time + "\\" + files[i].getName()));
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
            // 폴더가 있을시
        }
        // 썸머노트 내용중 임시저장 경로였던 id값들 실 저장된 폴더로 경로 내용 변경
        freeBoardContents = freeBoardContents.replaceAll(id, "freeBoard/" + freeBoardTitle + time);
        // 글 내용 검색용으로 만들기 위해 html 태그들 삭제
        String subject = freeBoardContents.replaceAll("<[^>]*>?", "");
        UserDTO userDTO=userService.getCurrentUser().get();
        FreeBoardDTO OriginalFreeBoardDTO = freeBoardService.findById(Long.parseLong(freeBoardId));
        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder().freeBoardTitle(freeBoardTitle).freeBoardContents(freeBoardContents)
                .userId(userDTO.getId()).nickname(userDTO.getNickname()).subject(subject).freeBoardCreatedTime(OriginalFreeBoardDTO.getFreeBoardUpdatedTime()).build();
         freeBoardDTO = freeBoardService.update(freeBoardDTO);
         System.out.println(freeBoardDTO);
        Long freeBoardNo =freeBoardDTO.getId();
        mv.setViewName("redirect:/freeBoard/detail/" + freeBoardNo);
        return mv;

    }


    @PostMapping("/removeTemporary")
    @ResponseBody
    public String removeTemporaryFile(@RequestParam(value = "id") String id){
        File file = new File("C:\\img\\" + id);
        if(file.exists()){ //파일존재여부확인
            if(file.isDirectory()){ //파일이 디렉토리인지 확인
                File[] files = file.listFiles();
                for(int i = 0; i < files.length; i++){
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
        return "sucess";
    }


    @GetMapping("/delete")
    public ModelAndView delete(ModelAndView mv, @RequestParam("freeBoardId") Long id) {
//        freeBoardService.delete(id);
        FreeBoardDTO freeBoardDTO = freeBoardService.findById(id);
        id=freeBoardDTO.getId();
        System.out.println(id);
        freeBoardService.delete(id);
//        String title = freeBoardDTO.getFreeBoardTitle();
//        String time = freeBoardDTO.getFreeBoardCreatedTime().toString().replaceAll(":","");
//        File file = new File("C:\\img\\freeBoard\\" + title + time);
//        if(file.exists()){
//            file.renameTo(new File("C:\\img\\freeBoard\\삭제예정[" + title + time +  "]"));
//        }
        mv.setViewName("redirect:/freeBoard/list");
        return mv;
    }

    // /board/paging?page=1
    @GetMapping("/list")
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
        model.addAttribute("keyword", searchKeyword);
        model.addAttribute("freeBoardDTOList", list);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/freeBoard/list";
    }

//    @PostMapping("/like")
//    public @ResponseBody int like(Long boardId, Long memberId) {
//        int result = bs.saveLike(boardId,memberId);
//        return result;
//    }

}

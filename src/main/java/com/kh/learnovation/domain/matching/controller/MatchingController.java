package com.kh.learnovation.domain.matching.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.matching.dto.MatchingCommnetDTO;
import com.kh.learnovation.domain.matching.dto.MatchingDTO;
import com.kh.learnovation.domain.matching.entity.Matching;
import com.kh.learnovation.domain.matching.entity.MatchingComment;
import com.kh.learnovation.domain.matching.service.MatchingService;
import com.kh.learnovation.domain.matching.service.MatchingCommentService;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.entity.Notice;
import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class MatchingController {
    @Autowired
    private MatchingService mService;
    @Autowired
    private MatchingCommentService mCommentService;

    @Autowired
    private UserService userService;

    @GetMapping("/matching/write")
    public ModelAndView writeView(ModelAndView mv){
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (!userDTO.isPresent()){
            mv.setViewName("common/error");
            return mv;
        }
        mv.addObject("random", UUID.randomUUID()).setViewName("matching/write");
        return mv;
    }
    @PostMapping("/matching/write")
    public ModelAndView write(ModelAndView mv
            , @RequestParam("content") String content
            , @RequestParam("title") String title
            , @RequestParam("id") String id
            , @RequestParam("userId") String userId){
        if(content.equals("") || title.equals("")){
            mv.setViewName("redirect:/matching/list");
            return mv;
        }
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (!userDTO.isPresent()){
            if(userId != userDTO.get().getId().toString()){
                mv.setViewName("common/error");
                return mv;
            }
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String time = ts.toString().replaceAll(":", "");
        String[] sList = content.split("\"");
        List<String> fileList = new ArrayList<String>();
        for(String aa : sList){
            if(aa.startsWith("/")){
                fileList.add(aa.substring(7));
            }
        }
        File diretory = new File("C:\\img\\matching\\" + title + time);
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
                            files[i].renameTo(new File("C:\\img\\matching\\" + title + time + "\\" + files[i].getName()));
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
        content = content.replaceAll(id, "matching/" + title + time);
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
        String subject = content.replaceAll("<[^>]*>?","");
        User user = User.builder().id(Long.parseLong(userId)).build();
        MatchingDTO matchingDTO = MatchingDTO.builder().title(title).content(content).user(user).subject(subject).createdAt(ts).status(0).build();
        long matchingNo = mService.insertMatching(matchingDTO).getId();
        mv.setViewName("redirect:/matching/detail?matchingNo=" + matchingNo);
        return mv;
    }
    @GetMapping("/matching/modify")
    public ModelAndView ModifyView(ModelAndView mv, @RequestParam("matchingNo") Long matchingNo){
        MatchingDTO matchingDTO = mService.selectOneMatching(matchingNo);
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            if(userDTO.get().getId() != matchingDTO.getUser().getId()){
                mv.setViewName("common/error");
                return mv;
            }
        }else{
            mv.setViewName("common/error");
            return mv;
        }
        mv.addObject("matching", matchingDTO).addObject("random", UUID.randomUUID()).setViewName("matching/modify");
        return mv;
    }
    @PostMapping("/matching/modify")
    public ModelAndView modify(ModelAndView mv
            , @RequestParam("content") String content
            , @RequestParam("title") String title
            , @RequestParam("id") String id
            , @RequestParam("userId") String userId
            , @RequestParam("matchingId") String matchingId
            , @RequestParam("createTime") Timestamp ts
            , @RequestParam("originalTitle") String originalTitle){
        if(content.equals("") || title.equals("")){
            mv.setViewName("redirect:/matching/list");
            return mv;
        }
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (!userDTO.isPresent()){
            if(userId != userDTO.get().getId().toString()){
                mv.setViewName("common/error");
                return mv;
            }
        }
        String time = ts.toString().replaceAll(":", "");
        if(!title.equals(originalTitle)){
            File originDirectory = new File("C:\\img\\matching\\" + originalTitle + time);
            File newDirectory = new File("C:\\img\\matching\\" + title + time);
            File[] originFiles = originDirectory.listFiles();
            if(originFiles != null){
                for(int i = 0; i < originFiles.length; i++){
                    originFiles[i].renameTo(new File("C:\\img\\matching\\" + title + time + "\\" + originFiles[i].getName()));
                }
                originDirectory.renameTo(newDirectory);
            }
        }
        // <p><img src="/image/notice/와우/efc877df-84ff-4495-9cad-7189dfe92d42플차.png"><img src="/image/notice/와우/f4e42ed1-ec45-4a05-a562-ab9fb06666be제목 없음.png"><br></p>
        // 썸머노트 글 작성시 내용 예시
        // "로 시작하는거로 짜름

        String[] sList = content.split("\"");
        List<String> fileList = new ArrayList<String>();
        for(String aa : sList){
            // /로 시작하는거 찾음
            if(aa.startsWith("/")){
                // /image/부분 공통이라 짜름
                fileList.add(aa.substring(7));
            }
        }
        // c 아래 img 아래 noitce아래 제목으로 파일 객체 생성
        File diretory = new File("C:\\img\\matching\\" + title + time);
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
                if(("notice/" + title + time + "/" + originalFiles[i].getName()).equals(fileName)){
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
                            files[i].renameTo(new File("C:\\img\\matching\\" + title + time + "\\" + files[i].getName()));
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
        content = content.replaceAll(id, "matching/" + title + time);
        // 글 내용 검색용으로 만들기 위해 html 태그들 삭제
        String subject = content.replaceAll("<[^>]*>?", "");
        User user = User.builder().id(Long.parseLong(userId)).build();
        MatchingDTO OriMatchingDTO = mService.selectOneMatching(Long.parseLong(matchingId));
        MatchingDTO matchingDTO = MatchingDTO.builder().id(Long.parseLong(matchingId)).title(title).content(content).user(user).subject(subject).createdAt(OriMatchingDTO.getCreatedAt()).build();
        long matchingNo = mService.updateMatching(matchingDTO).getId();
        mv.setViewName("redirect:/matching/detail?matchingNo=" + matchingNo);
        return mv;
    }
    @GetMapping("/matching/delete")
    public ModelAndView delete(ModelAndView mv, @RequestParam("matchingNo") Long matchingNo){
        MatchingDTO matchingDTO = mService.selectOneMatching(matchingNo);
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            if(!(userDTO.get().getRole().toString().equals("ADMIN")) && userDTO.get().getId() != matchingDTO.getUser().getId()){
                mv.setViewName("common/error");
                return mv;
            }
        }else{
            mv.setViewName("common/error");
            return mv;
        }
        matchingDTO.setStatus(1);
        matchingDTO = mService.updateMatching(matchingDTO);
        String title = matchingDTO.getTitle();
        String time = matchingDTO.getCreatedAt().toString().replaceAll(":", "");
        File file = new File("C:\\img\\matchingDTO\\" + title + time);
        if(file.exists()){
            file.renameTo(new File("C:\\img\\matchingDTO\\삭제예정[" + title + time +  "]"));
        }
        mv.setViewName("redirect:/matching/list");
        return mv;
    }
    @GetMapping("/matching/detail")
    public ModelAndView detail(ModelAndView mv
            , @RequestParam("matchingNo") Long matchingNo
            , @RequestParam(value="page", required = false, defaultValue = "1") String page
            , @RequestParam(value="keyword", required = false) String keyword){
        MatchingDTO matchingDTO = mService.selectOneMatching(matchingNo);
        mv.addObject("matching", matchingDTO).addObject("page", page).addObject("keyword",keyword).setViewName("matching/detail");
        return mv;
    }
    @GetMapping("/matching/list")
    public ModelAndView list(ModelAndView mv
            , @RequestParam(value="page", required = false, defaultValue = "1") String pNo
            , @RequestParam(value="keyword", required = false, defaultValue = "") String keyword){
        try {
            int page = Integer.parseInt(pNo);
            page = page <= 0 ? 1 : page;
            Pageable pageable = PageRequest.of(page - 1, 20, Sort.by(Sort.Direction.DESC,"createdAt"));
            Page<Matching> pMatching;
            if(keyword.equals("")){
                pMatching = mService.selectAllNotice(pageable);
            }else{
                mv.addObject("keyword", keyword);
                keyword = "%" + keyword + "%";
                pMatching = mService.selectAllNotice(keyword, pageable);
            }
            int totalPage = pMatching.getTotalPages();
            int startPage = (page - 1) / 5 * 5 + 1;
            int endPage = startPage + 4 > totalPage ? totalPage : startPage + 4;
            int[] navCount = new int[endPage - startPage + 1];
            for(int i = 0; i < navCount.length; i++ ){
                navCount[i] = startPage + i;
            }
            if(page <= totalPage){
                List<Matching> mList = pMatching.stream().collect(Collectors.toList());
                mv.addObject("mList", mList)
                        .addObject("navCount", navCount)
                        .addObject("page", page)
                        .addObject("totalPage", totalPage)
                        .setViewName("matching/list");
            }else{
                // 페이지 오버영역
            }
        }catch (NumberFormatException e){
            // 페이지가 숫자가 아닐경우
        }
        return mv;
    }

    @PostMapping(value = "/matching/ImgFileUpload", produces = "application/json; charset=utf8")
    @ResponseBody
    public String matchingImgUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") String id, HttpServletRequest request){
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

    @PostMapping("/matching/removeTemporary")
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

    //////////////// 게시판 끝 /////////////////////////////////////

    //////////////// 댓글 시작 /////////////////////////////////////
    @PostMapping("/matching/comment/insert")
    @ResponseBody
    public String commentWrite(@RequestParam("content") String content
            , @RequestParam("matchingNo") Long matchingNo){
        Matching matching = Matching.builder().id(matchingNo).build();
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            UserDTO DTO = userDTO.get();
            User user = User.builder()
                    .id(DTO.getId())
                    .build();
            MatchingCommnetDTO matchingCommnetDTO = MatchingCommnetDTO.builder()
                    .matching(matching)
                    .content(content)
                    .user(user)
                    .build();
            mCommentService.insertComment(matchingCommnetDTO);
            return "sucess";
        }
        return "fail";
    }

    @PostMapping("/matching/comment/list")
    @ResponseBody
    public String CommentList(@RequestParam("matchingNo") Long matchingNo){
        Matching matching = Matching.builder().id(matchingNo).build();
        List<MatchingComment> cList = mCommentService.selectList(matching);
        Gson gson = new Gson();
        return gson.toJson(cList);
    }

    @GetMapping("/matching/comment/delete")
    @ResponseBody
    public String deleteComment(@RequestParam("id") Long id, @RequestParam("userId") Long userId){
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            if(userId != userDTO.get().getId()){
                return "noPermission";
            }
        }else{
            return "noPermission";
        }
        MatchingCommnetDTO matchingCommnetDTO= MatchingCommnetDTO.builder().id(id).build();
        mCommentService.deleteComment(matchingCommnetDTO);
        return "success";
    }

    @PostMapping("/matching/comment/modify")
    @ResponseBody
    public String modifyCommnet(@RequestParam("id") Long id
            , @RequestParam("userId") Long userId
            , @RequestParam("content") String content){
        Optional<UserDTO> userDTO = userService.getCurrentUser();
        if (userDTO.isPresent()){
            if(userId != userDTO.get().getId()){
                return "noPermission";
            }
        }else{
            return "noPermission";
        }
        MatchingCommnetDTO matchingCommnetDTO = MatchingCommnetDTO.builder().id(id).content(content).build();
        mCommentService.updateComment(matchingCommnetDTO);
        return "success";
    }
}




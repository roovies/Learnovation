package com.kh.learnovation.domain.notice.controller;

import com.google.gson.JsonObject;
import com.kh.learnovation.domain.admin.entity.Admin;
import com.kh.learnovation.domain.notice.dto.NoticeDTO;
import com.kh.learnovation.domain.notice.service.NoticeServiceImpl;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class NoticeController {

    @Autowired
    NoticeServiceImpl nService;

    @GetMapping("/notice/write")
    public ModelAndView noticeWriteView(ModelAndView mv){
        mv.addObject("random", UUID.randomUUID()).setViewName("notice/write");
        return mv;
    }
    @PostMapping("/notice/write")
    public ModelAndView noticeWrite(ModelAndView mv
            , @RequestParam("content") String content
            , @RequestParam("title") String title
            , @RequestParam("id") String id
            , @RequestParam("adminId") String adminId) {
        String[] sList = content.split("\"");
        List<String> fileList = new ArrayList<String>();
        for(String aa : sList){
            if(aa.startsWith("/")){
                fileList.add(aa.substring(7));
            }
        }
        File diretory = new File("C:\\img\\notice\\" + title);
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
                            files[i].renameTo(new File("C:\\img\\notice\\" + title + "\\" + files[i].getName()));
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
        content = content.replace(id, "notice/" + title);
        Admin admin = Admin.builder().id(Long.parseLong(adminId)).build();
        NoticeDTO noticeDTO = NoticeDTO.builder().title(title).content(content).admin(admin).build();
        nService.insertNotice(noticeDTO);
        mv.setViewName("notice/write");
        return mv;
    }

    @PostMapping(value = "/notice/ImgFileUpload", produces = "application/json; charset=utf8")
    @ResponseBody
    public String noticeImgUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") String id, HttpServletRequest request){
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

    @GetMapping("/notice/detail")
    public ModelAndView noticeDetailView(ModelAndView mv, @RequestParam("noticeNo") Long noticeNo){
        NoticeDTO noticeDTO = nService.selectOneNotice(noticeNo);
        mv.addObject("notice", noticeDTO).setViewName("notice/detail");
        return mv;
    }
    
    // 로그인 한거 관리자인지 체크 해줘야 함
    @GetMapping("/notice/modify")
    public ModelAndView noticeModifyView(ModelAndView mv, @RequestParam("noticeNo") Long noticeNo){
        NoticeDTO noticeDTO = nService.selectOneNotice(noticeNo);
        mv.addObject("notice", noticeDTO).addObject("random", UUID.randomUUID()).setViewName("notice/modify");
        return mv;
    }

    @PostMapping("/notice/modify")
    public ModelAndView noticeModify(ModelAndView mv
            , @RequestParam("content") String content
            , @RequestParam("title") String title
            , @RequestParam("id") String id
            , @RequestParam("adminId") String adminId
            , @RequestParam("noticeId") String noticeId) {
        String[] sList = content.split("\"");
        List<String> fileList = new ArrayList<String>();
        for(String aa : sList){
            if(aa.startsWith("/")){
                fileList.add(aa.substring(7));
            }
        }
        File diretory = new File("C:\\img\\notice\\" + title);
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
                            files[i].renameTo(new File("C:\\img\\notice\\" + title + "\\" + files[i].getName()));
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
        content = content.replace(id, "notice/" + title);
        Admin admin = Admin.builder().id(Long.parseLong(adminId)).build();
        NoticeDTO noticeDTO = NoticeDTO.builder().id(Long.parseLong(noticeId)).title(title).content(content).admin(admin).build();
        nService.updateNotice(noticeDTO);
        mv.setViewName("notice/write");
        return mv;
    }
}


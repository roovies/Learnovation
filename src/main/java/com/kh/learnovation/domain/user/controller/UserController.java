package com.kh.learnovation.domain.user.controller;

import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 목록 조회
    @RequestMapping(value = "/admin/userList", method = RequestMethod.GET)
    public String userList(
            Model model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNum
    ) {

        List<UserDTO> userList = userService.getUserList(pageNum);
        Integer[] pageList = userService.getPageList(pageNum);

        model.addAttribute("userList", userList);
        model.addAttribute("pageList", pageList);

        return "admin/userList";
    }

    // 회원 이름으로 검색
    @RequestMapping(value = "/admin/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<UserDTO> userDTOList = userService.searchUsers(keyword);
        model.addAttribute("userList", userDTOList);

        return "admin/userList";
    }

    // 회원 상세정보 조회
    @RequestMapping(value = "/admin/detail/{no}", method = RequestMethod.GET)
    public String detail(@PathVariable("no") Long id, Model model) {
        UserDTO userDTO = userService.getPost(id);

        model.addAttribute("user", userDTO);
        return "admin/userDetail";
    }

    @PutMapping("/admin/update/{id}")
    public String userUpdate(UserDTO userDTO) {
        userService.savePost(userDTO);
        return "redirect:/admin/userList";
    }

    // 회원 탈퇴(삭제)
    @PostMapping("/admin/delete")
    public String userDelete(@RequestParam("selectedIds") List<Long> selectedIds) {
        userService.deleteByIdIn(selectedIds);
        return "redirect:/admin/userList";
    }

}

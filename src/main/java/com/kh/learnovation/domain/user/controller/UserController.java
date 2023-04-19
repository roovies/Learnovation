package com.kh.learnovation.domain.user.controller;

import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    회원 목록
    @RequestMapping(value = "/admin/management", method = RequestMethod.GET)
    public String userList(Model model) {

        List<UserDTO> userList = userService.getUserList();
        model.addAttribute("userList", userList);

        return "admin/usermanagement";
    }

    @DeleteMapping("/admin/{no}")
    public String userDelete(@PathVariable("no") Integer id) {
        userService.deleteUser(id);

        return "redirect:/admin/management";
    }

}

package com.kh.learnovation.domain.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kh.learnovation.domain.cart.entity.Cart;
import com.kh.learnovation.domain.cart.service.CartService;
import com.kh.learnovation.domain.course.entity.Course;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    @ResponseBody
    public String addCart(Long userId, Long courseId) {

        System.out.println("userId : " + userId + ", courseId : " + courseId);

        cartService.addCart(userId, courseId);

        return null;
    }

    @GetMapping("/list")
    public ModelAndView cartList(Long userNo, ModelAndView mv) {

        List<Course> courseList = new ArrayList<>();
        int totalPrice = 0;


        List<Cart> cartList = cartService.cartList(userNo);
        for(int i = 0; i < cartList.size(); i++) {
            courseList.add(cartList.get(i).getCartPk().getCourse());
            totalPrice += cartList.get(i).getCartPk().getCourse().getPrice();
        }

        mv.addObject("cartList", cartList).addObject("totalPrice", totalPrice).setViewName("payment/cartList");

        return mv;
    }

    @GetMapping("/remove")
    @ResponseBody
    public String cartRemove(Long userNo, Long courseNo) {

        cartService.removeCart(userNo, courseNo);

        return "success";
    }


}

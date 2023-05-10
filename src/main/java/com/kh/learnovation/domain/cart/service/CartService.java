package com.kh.learnovation.domain.cart.service;

import com.kh.learnovation.domain.cart.entity.Cart;

import java.util.List;

public interface CartService {
    void addCart(Long userId, Long courseId);

    List<Cart> cartList(Long userNo);

    void removeCart(Long userNo, Long courseNo);
}

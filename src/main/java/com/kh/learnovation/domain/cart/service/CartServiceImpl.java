package com.kh.learnovation.domain.cart.service;

import com.kh.learnovation.domain.cart.entity.Cart;
import com.kh.learnovation.domain.cart.entity.CartPk;
import com.kh.learnovation.domain.cart.repository.CartRepository;
import com.kh.learnovation.domain.course.entity.Course;
import com.kh.learnovation.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Override
    public void addCart(Long userId, Long courseId) {
        User user = User.builder().id(userId).build();
        Course course = Course.builder().id(courseId).build();
        CartPk cartPk = CartPk.builder().user(user).course(course).build();
        Cart cart = Cart.builder().cartPk(cartPk).build();
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> cartList(Long userNo) {
        List<Cart> cartList = cartRepository.findByUserNo(userNo);

        return cartList;
    }

    @Override
    public void removeCart(Long userNo, Long courseNo) {
        User user = User.builder().id(userNo).build();
        Course course = Course.builder().id(courseNo).build();
        CartPk cartPk = CartPk.builder().user(user).course(course).build();
        Cart cart = Cart.builder().cartPk(cartPk).build();

        cartRepository.delete(cart);
    }


}

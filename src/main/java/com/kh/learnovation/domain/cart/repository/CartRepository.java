package com.kh.learnovation.domain.cart.repository;

import com.kh.learnovation.domain.cart.entity.Cart;
import com.kh.learnovation.domain.cart.entity.CartPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartPk> {

    @Query(nativeQuery = true, value = "SELECT * FROM CARTS WHERE USER_ID = :id")
    List<Cart> findByUserNo(@Param("id") Long userNo);
}

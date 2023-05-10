package com.kh.learnovation.domain.cart.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "carts")
public class Cart {

    @EmbeddedId
    private CartPk cartPk;

    @Builder
    public Cart(CartPk cartPk) {
        this.cartPk = cartPk;
    }
}

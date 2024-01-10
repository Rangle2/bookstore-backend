package com.store.bookstore.controller;

import com.store.bookstore.entity.Cart;
import com.store.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;



    @GetMapping("/get/{cartId}")
    public Cart getCartById(Long cartId){
        return cartService.getCardById(cartId);
    }

    @GetMapping("/get/user/{userId}")
    public Cart getUserCartById(Long userId){
        return cartService.getUserCart(userId);
    }
}

package com.store.bookstore.service;

import com.store.bookstore.entity.Cart;
import com.store.bookstore.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    public Cart getCardById(Long cartId){
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart getUserCart(Long userId){
        Cart userCart = cartRepository.findCartByUserId(userId);
        if (userCart == null){
            throw new RuntimeException("User Not found");
        }
        return userCart;
    }
}

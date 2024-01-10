package com.store.bookstore.controller;

import com.store.bookstore.entity.Cart;
import com.store.bookstore.request.CartRequest;
import com.store.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping("/get/{cartId}")
    public Cart getCartById(@PathVariable Long cartId){
        return cartService.getCardById(cartId);
    }

    @GetMapping("/get/user/{userId}")
    public Cart getUserCartById(@PathVariable Long userId){
        return cartService.getUserCart(userId);
    }

    @PostMapping("/create/{userId}")
    public Cart createNewCart(@PathVariable Long userId, @RequestBody CartRequest cartRequest){
        return cartService.createUserCart(userId,cartRequest);
    }

    @PutMapping("/edit/{userId}")
    public Cart editNewCart(@PathVariable Long userId, @RequestBody CartRequest cartRequest){
        return cartService.editUserCart(userId,cartRequest);
    }


    @DeleteMapping("/delete/{cartId}")
    public void deleteUserCartById(@PathVariable Long cartId){
        cartService.deleteUserCartById(cartId);
    }
}

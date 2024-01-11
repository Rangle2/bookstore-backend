package com.store.bookstore.service;

import com.store.bookstore.entity.Cart;
import com.store.bookstore.entity.Product;
import com.store.bookstore.entity.User;
import com.store.bookstore.repos.CartRepository;
import com.store.bookstore.repos.UserRepository;
import com.store.bookstore.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    public Cart getCardById(Long cartId){
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public  Cart getUserCart(Long userId){
        Cart userCart = cartRepository.findCartByUserId(userId);
        if (userCart == null){
            throw new RuntimeException("User Not found");
        }
        return userCart;
    }

    public List<Cart> findAllByUserId(Long userId){
        return cartRepository.findAllByUserId(userId);
    }

    public Cart createUserCart(Long userId, CartRequest cartRequest) {
        // Retrieve the user by userId from the userRepository
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            // If the user exists, get the user object
            User user = optionalUser.get();

            // Check if there is an existing cart for the given product name and user
            Optional<Cart> existingCart = cartRepository.findByUserIdAndProductName(userId, cartRequest.getProductName());

            if (existingCart.isPresent()) {
                // If there is an existing cart for the product, increment the quantity
                Cart cartToUpdate = existingCart.get();
                cartToUpdate.setQuantity(cartToUpdate.getQuantity() + 1);
                cartRepository.save(cartToUpdate);
                return cartToUpdate;
            } else {
                // If there is no existing cart, create a new cart
                Cart newCart = new Cart();
                newCart.setProductName(cartRequest.getProductName());
                newCart.setPrice(cartRequest.getPrice());
                newCart.setQuantity(cartRequest.getQuantity());
                newCart.setUserId(userId);
                newCart.setProductId(cartRequest.getProductId());
                newCart.setProductImg(cartRequest.getProductImg());
                cartRepository.save(newCart);
                return newCart;
            }
        } else {
            // If the user does not exist, throw a runtime exception
            throw new RuntimeException("User Not Found");
        }
    }


    

    public Cart editUserCart(Long userId, CartRequest cartRequest){
        Cart currentUserCart = cartRepository.findCartByUserId(userId);
        if (currentUserCart != null){
            currentUserCart.setProductName(cartRequest.getProductName());
            currentUserCart.setPrice(cartRequest.getPrice());
            currentUserCart.setQuantity(cartRequest.getQuantity());
            currentUserCart.setUserId(userId);
            return currentUserCart;
        }else {
            throw new RuntimeException("Cart not found");
        }

    }

    public void deleteUserCartById(Long cartId){
        cartRepository.deleteById(cartId);
    }




}

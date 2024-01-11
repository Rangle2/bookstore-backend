package com.store.bookstore.repos;

import com.store.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.userId = :userId")
    Cart findCartByUserId(@Param("userId") Long userId);

    List<Cart> findAllByUserId(Long userId);

    Optional<Cart> findByUserIdAndProductName(Long userId, String productName);
}

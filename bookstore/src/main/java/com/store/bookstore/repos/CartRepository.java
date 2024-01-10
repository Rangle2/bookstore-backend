package com.store.bookstore.repos;

import com.store.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c JOIN c.users u WHERE u.userId = :userId")
    Cart findCartByUserId(@Param("userId") Long userId);
}

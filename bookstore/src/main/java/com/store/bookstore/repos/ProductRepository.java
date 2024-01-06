package com.store.bookstore.repos;

import com.store.bookstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getByProductId(Long id);
    List<Product> findByCategoryCategoryId(Long categoryId);
}

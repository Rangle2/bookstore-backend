package com.store.bookstore.repos;

import com.store.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getByCategoryId(Long id);

    Category getByCategoryName(String name);

}

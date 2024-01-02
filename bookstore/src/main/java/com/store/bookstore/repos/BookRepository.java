package com.store.bookstore.repos;

import com.store.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository <Book, Long>{
    Book getByBookId(Long id);
}

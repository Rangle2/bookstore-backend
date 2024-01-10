package com.store.bookstore.repos;

import com.store.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndEmail(String username, String email);

    User deleteByUsername(String username);
}

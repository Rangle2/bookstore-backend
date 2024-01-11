package com.store.bookstore.repos;

import com.store.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT c.userId FROM User c WHERE c.username = :username")
    Long findUserIdByUsername(@Param("username") String username);
    @Query("SELECT c.firstname FROM User c WHERE c.username = :username")
    String findUserFirstName(@Param("username") String username);

    @Query("SELECT c.lastname FROM User c WHERE c.username = :username")
    String findUserLastName(@Param("username") String username);

    User findByUsernameAndEmail(String username, String email);

    User deleteByUsername(String username);
}

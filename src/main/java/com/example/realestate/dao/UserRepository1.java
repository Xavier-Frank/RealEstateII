package com.example.realestate.dao;

import com.example.realestate.model.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository1 extends JpaRepository<User1, Long> {
    @Query("SELECT u FROM User1 u WHERE u.email = ?1")
    User1 findByEmail(String email);
}

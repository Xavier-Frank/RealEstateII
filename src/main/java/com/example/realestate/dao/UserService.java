package com.example.realestate.dao;

import com.example.realestate.model.User1;

import java.util.Optional;

public interface UserService {

    public User1 findUserByEmail(String email);

    public Optional<User1> findUserByResetToken(String resetToken);

    public  void saveUser(User1 user1);

    void save(User1 user1);
}

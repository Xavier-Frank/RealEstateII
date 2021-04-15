package com.example.realestate.service;

import com.example.realestate.dao.UserRepository1;
import com.example.realestate.dao.UserService;
import com.example.realestate.model.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository1 userRepository1;

    @Override
    public User1 findUserByEmail(String email) {
        return userRepository1.findByEmail(email);
    }

    @Override
    public Optional<User1> findUserByResetToken(String resetToken) {
        return userRepository1.findByResetToken(resetToken);
    }

    @Override
    public void saveUser(User1 user1) {
        userRepository1.save(user1);
    }

    @Override
    public void save(User1 user1) {
        userRepository1.save(user1);

    }
}

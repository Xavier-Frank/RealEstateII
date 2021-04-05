package com.example.realestate.service;

import com.example.realestate.dao.UserRepository1;
import com.example.realestate.model.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository1 userRepository1;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User1 user1 = userRepository1.findByEmail(email);
        if (user1 == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new CustomUserDetails(user1);

    }
}

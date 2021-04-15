package com.example.realestate.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "judithakinyi2010";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }

}

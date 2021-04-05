package com.example.realestate;

import com.example.realestate.dao.UserRepository1;
import com.example.realestate.model.User1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepository1Tests {

    @Autowired
    private UserRepository1 userRepository1;

    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateUser(){
        User1 user1 = new User1();
        user1.setEmail("joseph123@gmail.com");
        user1.setFirstname("john");
        user1.setLastname("doe");
        user1.setPhonenumber("+254797899972");
//        user1.setPassword("kennedy");

        String rawPassword= "jesu";
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(rawPassword);
        user1.setPassword(encodedPassword);

        User1 savedUser= userRepository1.save(user1);

        //asserting the user
//        User1 existingUser= entityManager.find(User1.class, savedUser.getId());
//
//        assertThat(existingUser.getEmail().equals(savedUser));

    }
    @Test
    public void testFindUserByEmail(){
        String email = "johndeer@gmail.com";

        User1 user1 = userRepository1.findByEmail(email);
        assertThat(user1).isNotNull();
    }
}

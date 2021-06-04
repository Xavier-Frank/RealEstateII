package com.example.realestate;

import com.example.realestate.dao.TenantsRepository;
import com.example.realestate.model.Tenants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TenantsRepositoryTests {

    @Autowired
    private TenantsRepository tenantsRepository;

    @Autowired
    private TestEntityManager entityManager;
    @Test
    public void testCreateUser(){
        Tenants tenants = new Tenants();
        tenants.setEmail("joseph123@gmail.com");
        tenants.setFirstname("john");
        tenants.setLastname("doe");
        tenants.setPhonenumber("+254797899972");
//        user1.setPassword("kennedy");

        String rawPassword= "jesu";
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(rawPassword);
        tenants.setPassword(encodedPassword);

        Tenants savedUser= tenantsRepository.save(tenants);

        //asserting the user
//        User1 existingUser= entityManager.find(User1.class, savedUser.getId());
//
//        assertThat(existingUser.getEmail().equals(savedUser));

    }
    @Test
    public void testFindUserByEmail(){
        String email = "johndeer@gmail.com";

        Tenants tenants = tenantsRepository.findByEmail(email);
        assertThat(tenants).isNotNull();
    }
}

package com.example.realestate.service;

import com.example.realestate.dao.TenantsRepository;
import com.example.realestate.model.Tenants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TenantsRepository tenantsRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Tenants tenants = tenantsRepository.findByEmail(email);
        if (tenants == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new CustomUserDetails(tenants);

    }
}

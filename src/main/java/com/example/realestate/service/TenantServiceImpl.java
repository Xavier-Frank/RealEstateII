package com.example.realestate.service;

import com.example.realestate.dao.TenantsRepository;
import com.example.realestate.dao.TenantService;
import com.example.realestate.model.Tenants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantsRepository tenantsRepository;

    @Override
    public Tenants findUserByEmail(String email) {
        return tenantsRepository.findByEmail(email);
    }

    @Override
    public Optional<Tenants> findUserByResetToken(String resetToken) {
        return tenantsRepository.findByResetToken(resetToken);
    }

    @Override
    public void saveUser(Tenants tenants) {
        tenantsRepository.save(tenants);
    }

    @Override
    public void save(Tenants tenants) {
        tenantsRepository.save(tenants);

    }
}

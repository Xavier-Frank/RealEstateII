package com.example.realestate.dao;

import com.example.realestate.model.Tenants;

import java.util.Optional;

public interface TenantService {

    public Tenants findUserByEmail(String email);

    public Optional<Tenants> findUserByResetToken(String resetToken);

    public  void saveUser(Tenants tenants);

    void save(Tenants tenants);
}

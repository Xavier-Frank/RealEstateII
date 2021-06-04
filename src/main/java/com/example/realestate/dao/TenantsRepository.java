package com.example.realestate.dao;

import com.example.realestate.model.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantsRepository extends JpaRepository<Tenants, Long> {
    @Query("SELECT u FROM Tenants u WHERE u.email = ?1")
    Tenants findByEmail(String email);

    Optional<Tenants> findByResetToken(String resetToken);
}

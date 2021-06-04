package com.example.realestate.dao;

import com.example.realestate.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends JpaRepository<Deposit, Long> {

}

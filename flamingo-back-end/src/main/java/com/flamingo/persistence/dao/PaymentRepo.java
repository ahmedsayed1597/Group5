package com.flamingo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long>{
    
}

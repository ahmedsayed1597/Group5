package com.flamingo.persistence.dao;


import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.Payment;

@Repository
public interface PaymentRepo  extends JpaRepository<Payment,Long>{
    
}

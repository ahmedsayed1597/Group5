package com.flamingo.persistence.dao;

<<<<<< khames

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.Payment;


public interface PaymentRepo extends JpaRepository<Payment, Long>{
    
}

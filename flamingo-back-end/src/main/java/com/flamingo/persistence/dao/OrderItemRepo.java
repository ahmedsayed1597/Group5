package com.flamingo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flamingo.persistence.entities.OrderItem;


public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
    
}

package com.example.demo.repository.dao.interfaces;
import com.example.demo.repository.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsDao  extends JpaRepository<OrderDetails,Integer>{
}

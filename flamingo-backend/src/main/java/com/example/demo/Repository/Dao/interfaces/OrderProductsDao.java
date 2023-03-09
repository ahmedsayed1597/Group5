package com.example.demo.repository.dao.interfaces;
import com.example.demo.repository.entities.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsDao  extends JpaRepository<OrderProducts,Integer> {
}

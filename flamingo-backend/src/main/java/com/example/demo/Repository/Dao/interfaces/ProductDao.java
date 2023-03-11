package com.example.demo.repository.dao.interfaces;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entities.Product;

public interface ProductDao extends JpaRepository<Product,Integer>
{


}

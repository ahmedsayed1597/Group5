package com.example.demo.Repository.Dao.interfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Repository.Entities.Product;

public interface ProductDao extends JpaRepository<Product,Integer>
{


}

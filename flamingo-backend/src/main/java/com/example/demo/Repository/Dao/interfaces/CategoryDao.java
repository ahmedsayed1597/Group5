package com.example.demo.Repository.Dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Repository.Entities.*;

public interface CategoryDao extends JpaRepository<Category,Integer> {
}

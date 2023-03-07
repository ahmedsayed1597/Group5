package com.example.demo.repository.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entities.Category;

public interface CategoryDao  extends JpaRepository<Category,Integer> {
}

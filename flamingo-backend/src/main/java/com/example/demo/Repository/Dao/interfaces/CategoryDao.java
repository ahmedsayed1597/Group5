package com.example.demo.repository.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entities.Category;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDao  extends JpaRepository<Category,Integer> {

//    @Query("select c from Category c where c.categoryName = ?1")
    public Category getCategoryByCategoryName(String categoryName);
}

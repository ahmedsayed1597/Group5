package com.flamingo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.Category;

public interface categoryRepo extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);
}

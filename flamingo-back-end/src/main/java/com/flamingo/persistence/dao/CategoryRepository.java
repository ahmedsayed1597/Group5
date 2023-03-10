package com.flamingo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    
    Category getCategoryByCategoryName(String categoryName );

}

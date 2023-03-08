package com.example.demo.repository.dao.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entities.Category;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDao  extends JpaRepository<Category,Integer> {

//    @Query("select c from Category c where c.categoryName = ?1")
    public Category getCategoryByCategoryName(String categoryName );

    // public List<Category> getAllWithPaging(int start , int size);


}

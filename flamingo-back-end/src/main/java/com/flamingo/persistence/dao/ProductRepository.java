package com.flamingo.persistence.dao;

import com.flamingo.persistence.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.Product;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {
    
    @Query("select p from Product p where p.productName like ?1")
    Page<Product> findProductByProductNameLike(String productName, Pageable pageDetails);

    @Query("select p from Product p where p.category.categoryId = ?1")
    Page<Product> findProductsByCategoryId(Long categoryId, Pageable pageDetails);


}

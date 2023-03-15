package com.flamingo.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword%")
    Page<Product> findProductByProductNameLike(String keyword, Pageable pageDetails);

    @Query("select p from Product p where p.category.categoryId = ?1")
    Page<Product> findProductsByCategoryId(Long categoryId, Pageable pageDetails);

    Page<Product>  findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String ProductName,
            String Description,
            Pageable pageDetails
            );

    @Transactional
    @Modifying
    @Query("delete from Product p where p.productId = ?1")
    void deleteProductByproductId(Long productId);
}

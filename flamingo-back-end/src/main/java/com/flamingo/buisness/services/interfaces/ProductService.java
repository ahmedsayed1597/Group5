package com.flamingo.buisness.services.interfaces;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.flamingo.persistence.entities.Product;
import com.flamingo.presentation.dto.ProductDTO;
import com.flamingo.presentation.responseviewmodel.ProductResponse;

public interface ProductService{

    ProductDTO addProduct(Long categoryId, Product product);


	ProductDTO addProductWithImage(Long categoryId
			, @RequestParam("productName") String productName,
								   String description,
								   int quantity,
								   double price,
								   MultipartFile image) throws IOException;

	ProductDTO addProductWithImageWithJson(Long categoryId
			, Product product,
										   MultipartFile image) throws IOException;

	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String field, String orderBy);

	ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String field,
			String orderBy);

    ProductDTO updateProduct(Long productId, Product product);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;

	ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);

	String deleteProduct(Long productId);

	public byte[] downloadImages(Long productId) throws IOException;
}
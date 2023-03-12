package com.flamingo.buisness.services.interfaces;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.flamingo.persistence.entities.Product;
import com.flamingo.presentation.dto.productDDDTO;
import com.flamingo.presentation.responseviewmodel.ProductResponse;

public interface ProductService{


	productDDDTO addProduct(Long categoryId
			, Product product,
										   MultipartFile image) throws IOException;

	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String field, String orderBy);

	ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String field,
			String orderBy);

			productDDDTO updateProduct(Long productId, Product product);

			productDDDTO updateProductImage(Long productId, MultipartFile image) throws IOException;

	ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
			String sortOrder);

	String deleteProduct(Long productId);

	public byte[] downloadImages(Long productId) throws IOException;
}
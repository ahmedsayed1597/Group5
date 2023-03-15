package com.flamingo.buisness.services.interfaces;

import java.util.List;

import com.flamingo.presentation.dto.CartDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {
    CartDTO addProductToCart(Long cartId, Long productId, Integer quantity);
	
	List<CartDTO> getAllCarts();
	
	CartDTO getCart(String emailId, Long cartId);
	
	CartDTO updateProductQuantityInCart(Long cartId, Long productId, Integer quantity);
	
	void updateProductInCarts(Long cartId, Long productId);

	@Transactional
	@Modifying
	String deleteProductFromCart(Long cartId, Long productId);
}

package com.flamingo.presentation.dto;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long cartId;
	private Double totalPrice = 0.0;
	private List<ProductDto> products = new ArrayList<>();
}

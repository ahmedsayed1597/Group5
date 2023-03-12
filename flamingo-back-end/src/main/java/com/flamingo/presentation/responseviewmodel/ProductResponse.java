package com.flamingo.presentation.responseviewmodel;


import java.util.List;

import com.flamingo.presentation.dto.productDDDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

	private List<productDDDTO> Data;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private boolean lastPage;
	
}


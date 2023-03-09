package com.example.demo.presentation.responseViewModel;

import java.util.List;

import com.example.demo.buisness.dtos.responseDTOs.ProductResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String message;
    private List<ProductResponseDto> data;
}

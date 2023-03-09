package com.example.demo.buisness.dtos.responseDTOs;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    
    private int id;
    private String categoryName;
    private String image;
    private List<ProductResponseDto> products = new ArrayList<>(0);
}

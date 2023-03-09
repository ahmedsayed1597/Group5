package com.example.demo.presentation.responseViewModel;

import java.util.List;

import com.example.demo.buisness.dtos.responseDTOs.CategoryResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private String message;
    private List<CategoryResponseDto>  categories;
}

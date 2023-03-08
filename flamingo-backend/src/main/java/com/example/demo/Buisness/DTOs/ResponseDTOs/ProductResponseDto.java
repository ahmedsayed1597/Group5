package com.example.demo.buisness.dtos.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDto {
    private int id;
    private String name;
    private String description;
    private int price;

}

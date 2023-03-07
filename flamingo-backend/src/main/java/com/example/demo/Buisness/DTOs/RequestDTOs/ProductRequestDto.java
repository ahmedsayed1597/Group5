package com.example.demo.buisness.dtos.requestDTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private String name;
    private String description;
    private int price;
    private int quantity;
    private int categoryid;

    
}

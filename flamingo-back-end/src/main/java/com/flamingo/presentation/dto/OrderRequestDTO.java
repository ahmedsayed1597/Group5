package com.flamingo.presentation.dto;

import com.flamingo.persistence.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequestDTO {

    private String email;
    private OrderItem orderItem ;
    private LocalDate orderDate;
    private Double totalAmount;
    private String orderStatus;
}

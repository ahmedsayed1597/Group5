package com.flamingo.presentation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddressDTO {
    
    private String street;

	private String city;

	private String country;
}

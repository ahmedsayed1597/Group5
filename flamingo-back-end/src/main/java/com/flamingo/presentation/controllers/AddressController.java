package com.flamingo.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.buisness.services.AddressService;
import com.flamingo.persistence.entities.Address;
import com.flamingo.presentation.dto.AddressDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin/addresses")
// @SecurityRequirement(name = "E-Commerce Application")
public class AddressController {


    @Autowired
	private AddressService addressService;



    @GetMapping("/addresses")
	public ResponseEntity<List<AddressDTO>> getAddresses() {
		List<AddressDTO> addressDTOs = addressService.getAddresses();
		
		return new ResponseEntity<List<AddressDTO>>(addressDTOs, HttpStatus.FOUND);
	}
	
	@GetMapping("/addresses/{addressId}")
	public ResponseEntity<AddressDTO> getAddress(@PathVariable Long addressId) {
		AddressDTO addressDTO = addressService.getAddress(addressId);
		
		return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.FOUND);
	}

    @PostMapping("/address")
	public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
		AddressDTO savedAddressDTO = addressService.createAddress(addressDTO);
		
		return new ResponseEntity<AddressDTO>(savedAddressDTO, HttpStatus.CREATED);
	}


    @PutMapping("/addresses/{addressId}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
		AddressDTO addressDTO = addressService.updateAddress(addressId, address);
		
		return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/addresses/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
		String status = addressService.deleteAddress(addressId);
		
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
    
}

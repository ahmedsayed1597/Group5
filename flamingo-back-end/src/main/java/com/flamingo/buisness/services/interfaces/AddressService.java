package com.flamingo.buisness.services.interfaces;

import java.util.List;

import com.flamingo.persistence.entities.Address;
import com.flamingo.presentation.dto.AddressDTO;

public interface AddressService {

    AddressDTO createAddress(AddressDTO addressDTO);
	
	List<AddressDTO> getAddresses();
	
	AddressDTO getAddress(Long addressId);
	
	AddressDTO updateAddress(Long addressId, Address address);
	
	String deleteAddress(Long addressId);
    
}

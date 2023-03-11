package com.flamingo.buisness.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flamingo.exception.APIException;
import com.flamingo.persistence.dao.AddressRepo;
import com.flamingo.persistence.dao.RoleRepo;
import com.flamingo.persistence.dao.UserRepo;
import com.flamingo.persistence.entities.Address;
import com.flamingo.persistence.entities.Cart;
import com.flamingo.persistence.entities.Role;
import com.flamingo.persistence.entities.User;
import com.flamingo.presentation.dto.AddressDTO;
import com.flamingo.presentation.dto.UserDTO;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    public static final Long ADMIN_ID = 101L;
	public static final Long USER_ID = 102L;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        try {
            User user = modelMapper.map(userDTO, User.class);

            Cart cart = new Cart();
            user.setCart(cart);

            Role role = roleRepo.findById(USER_ID).get();
            user.getRoles().add(role);

            String country = userDTO.getAddress().getCountry();
            String state = userDTO.getAddress().getState();
            String city = userDTO.getAddress().getCity();
            String pincode = userDTO.getAddress().getPincode();
            String street = userDTO.getAddress().getStreet();
            String buildingName = userDTO.getAddress().getBuildingName();

            Address address = addressRepo.findByCountryAndStateAndCityAndPincodeAndStreetAndBuildingName(country, state,
                    city, pincode, street, buildingName);

            if (address == null) {
                address = new Address(country, state, city, pincode, street, buildingName);

                address = addressRepo.save(address);
            }

            user.setAddresses(List.of(address));

            User registeredUser = userRepo.save(user);

            cart.setUser(registeredUser);

            userDTO = modelMapper.map(registeredUser, UserDTO.class);

            userDTO.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDTO.class));

            return userDTO;
        } catch (DataIntegrityViolationException e) {
            throw new APIException("User already exists with emailId: " + userDTO.getEmail());
        }

    }

    // @Override
    // public com.flamingo.buisness.services.UserDTO registerUser(com.flamingo.buisness.services.UserDTO userDTO) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'registerUser'");
    // }

    // @Override
    // public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    // }

    // @Override
    // public com.flamingo.buisness.services.UserDTO getUserById(Long userId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    // }

    // @Override
    // public com.flamingo.buisness.services.UserDTO updateUser(Long userId,
    //         com.flamingo.buisness.services.UserDTO userDTO) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    // }

    // @Override
    // public String deleteUser(Long userId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    // }
}
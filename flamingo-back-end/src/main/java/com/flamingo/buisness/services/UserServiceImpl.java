package com.flamingo.buisness.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.flamingo.buisness.services.interfaces.CartService;
import com.flamingo.exception.APIException;
import com.flamingo.exception.ResourceNotFoundException;
import com.flamingo.persistence.dao.AddressRepo;
import com.flamingo.persistence.dao.RoleRepo;
import com.flamingo.persistence.dao.UserRepo;
import com.flamingo.persistence.entities.Cart;
import com.flamingo.persistence.entities.CartItem;
import com.flamingo.persistence.entities.Role;
import com.flamingo.persistence.entities.User;
import com.flamingo.presentation.dto.AddressDTO;
import com.flamingo.presentation.dto.CartDTO;
import com.flamingo.presentation.dto.productDDDTO;
import com.flamingo.presentation.dto.UserDTO;
import com.flamingo.presentation.dto.UserRequestDTO;
import com.flamingo.presentation.dto.UserResponse;

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
    public UserRequestDTO registerUser(UserRequestDTO userRequestDTO) {

        try {
            User user = modelMapper.map(userRequestDTO, User.class);

            Cart cart = new Cart();
            user.setCart(cart);

            Role role = roleRepo.findById(USER_ID).get();
            user.getRoles().add(role);



            User registeredUser = userRepo.save(user);

            cart.setUser(registeredUser);

            userRequestDTO = modelMapper.map(registeredUser, UserRequestDTO.class);


            return userRequestDTO;
        } catch (DataIntegrityViolationException e) {
            throw new APIException("User already exists with emailId: " + userRequestDTO.getEmail());
        }

    }

    @Override
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
		
		Page<User> pageUsers = userRepo.findAll(pageDetails);
		
		List<User> users = pageUsers.getContent();

		if (users.size() == 0) {
			throw new APIException("No User exists !!!");
		}

		List<UserRequestDTO> userDTOs = users.stream().map(user -> {
			UserRequestDTO dto = modelMapper.map(user, UserRequestDTO.class);

	

			CartDTO cart = modelMapper.map(user.getCart(), CartDTO.class);

			List<productDDDTO> products = user.getCart().getCartItems().stream()
					.map(item -> modelMapper.map(item.getProduct(), productDDDTO.class)).collect(Collectors.toList());


			return dto;

		}).collect(Collectors.toList());

		UserResponse userResponse = new UserResponse();
		
		userResponse.setContent(userDTOs);
		userResponse.setPageNumber(pageUsers.getNumber());
		userResponse.setPageSize(pageUsers.getSize());
		userResponse.setTotalElements(pageUsers.getTotalElements());
		userResponse.setTotalPages(pageUsers.getTotalPages());
		userResponse.setLastPage(pageUsers.isLast());
		
		return userResponse;
	}

	@Override
	public UserDTO getUserById(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		userDTO.setAddress(modelMapper.map(user.getAddresses().stream().findFirst().get(), AddressDTO.class));

		CartDTO cart = modelMapper.map(user.getCart(), CartDTO.class);

		List<productDDDTO> products = user.getCart().getCartItems().stream()
				.map(item -> modelMapper.map(item.getProduct(), productDDDTO.class)).collect(Collectors.toList());

		userDTO.setCart(cart);

		userDTO.getCart().setProducts(products);

		return userDTO;
	}

	@Override
	public UserRequestDTO updateUser(Long userId, UserRequestDTO userDTO) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		String encodedPass = passwordEncoder.encode(userDTO.getPassword());

		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobileNumber(userDTO.getMobileNumber());
		user.setEmail(userDTO.getEmail());
		user.setPassword(encodedPass);
		userDTO = modelMapper.map(user, UserRequestDTO.class);
		return userDTO;
	}

	@Override
	public String deleteUser(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		List<CartItem> cartItems = user.getCart().getCartItems();
		Long cartId = user.getCart().getCartId();

		cartItems.forEach(item -> {

			Long productId = item.getProduct().getProductId();

			cartService.deleteProductFromCart(cartId, productId);
		});

		userRepo.delete(user);

		return "User with userId " + userId + " deleted successfully!!!";
	}
}
package com.flamingo.buisness.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.flamingo.buisness.exception.notFoundException;
import com.flamingo.presentation.dto.OrderRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import com.flamingo.buisness.exception.APIException;
import com.flamingo.buisness.exception.ResourceNotFoundException;
import com.flamingo.buisness.services.UserService;
import com.flamingo.buisness.services.interfaces.CartService;
import com.flamingo.buisness.services.interfaces.OrderService;
import com.flamingo.persistence.dao.OrderItemRepo;
import com.flamingo.persistence.dao.OrderRepo;
import com.flamingo.persistence.dao.*;
import com.flamingo.persistence.entities.Cart;
import com.flamingo.persistence.entities.CartItem;
import com.flamingo.persistence.entities.Order;
import com.flamingo.persistence.entities.OrderItem;
import com.flamingo.persistence.entities.Product;
import com.flamingo.presentation.dto.OrderDTO;
import com.flamingo.presentation.dto.OrderItemDTO;
import com.flamingo.presentation.responseviewmodel.OrderResponse;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	public final UserRepo userRepo;

	public final CartRepo cartRepo;

	public final OrderRepo orderRepo;


	public final OrderItemRepo orderItemRepo;

	public final CartItemRepo cartItemRepo;

	public final UserService userService;

	public final CartService cartService;

	public final ModelMapper modelMapper;

	@Override
	public OrderDTO placeOrder(String emailId, Long cartId) {

		Cart cart = cartRepo.findById( cartId).orElseThrow(()->new notFoundException("not found "));

		if (cart == null) {
			throw new ResourceNotFoundException("Cart", "cartId", cartId);
		}

		Order order = new Order();

		order.setEmail(emailId);
		order.setOrderDate(LocalDate.now());

		order.setTotalAmount(cart.getTotalPrice());
		order.setOrderStatus("Order Accepted !");


		Order savedOrder = orderRepo.save(order);

		List<CartItem> cartItems = cart.getCartItems();

		if (cartItems.size() == 0) {
			throw new APIException("Cart is empty");
		}

		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();

			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setOrderedProductPrice(cartItem.getProductPrice());
			orderItem.setOrder(savedOrder);

			orderItems.add(orderItem);
		}

		orderItems = orderItemRepo.saveAll(orderItems);

		cart.getCartItems().forEach(item -> {
			int quantity = item.getQuantity();

			Product product = item.getProduct();

			cartService.deleteProductFromCart(cartId, item.getProduct().getProductId());

			product.setQuantity(product.getQuantity() - quantity);
		});

		OrderDTO orderDTO = modelMapper.map(savedOrder, OrderDTO.class);
		
		orderItems.forEach(item -> orderDTO.getOrderItems().add(modelMapper.map(item, OrderItemDTO.class)));


		return orderDTO;
	}

	@Override
	public OrderRequestDTO placeOrder(String emailId, OrderRequestDTO orderRequestDTO) {
		Order order = new Order();

		order.setEmail(emailId);
		order.setOrderDate(LocalDate.now());
		order.setTotalAmount(orderRequestDTO.getTotalAmount());
		order.setOrderStatus("Order Accepted !");

		Order savedOrder = orderRepo.save(order);

		OrderItem orderItem=orderRequestDTO.getOrderItem();
		orderItem.setOrder(savedOrder);
		orderItem.setProduct(orderRequestDTO.getOrderItem().getProduct());
		 orderItem =orderItemRepo.save(orderItem);
		OrderDTO orderDTO = modelMapper.map(savedOrder, OrderDTO.class);

		 orderDTO.getOrderItems().add(modelMapper.map(orderItem, OrderItemDTO.class));

		return modelMapper.map(orderDTO,OrderRequestDTO.class);
	}

	@Override
	public List<OrderDTO> getOrdersByUser(String emailId) {
		List<Order> orders = orderRepo.findAllByEmail(emailId);

		List<OrderDTO> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class))
				.collect(Collectors.toList());

		if (orderDTOs.size() == 0) {
			throw new APIException("No orders placed yet by the user with email: " + emailId);
		}

		return orderDTOs;
	}

	@Override
	public OrderDTO getOrder(String emailId, Long orderId) {

		Order order = orderRepo.findOrderByEmailAndOrderId(emailId, orderId);

		if (order == null) {
			throw new ResourceNotFoundException("Order", "orderId", orderId);
		}

		return modelMapper.map(order, OrderDTO.class);
	}

	@Override
	public OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

		Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

		Page<Order> pageOrders = orderRepo.findAll(pageDetails);

		List<Order> orders = pageOrders.getContent();

		List<OrderDTO> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class))
				.collect(Collectors.toList());
		
		if (orderDTOs.size() == 0) {
			throw new APIException("No orders placed yet by the users");
		}

		OrderResponse orderResponse = new OrderResponse();
		
		orderResponse.setContent(orderDTOs);
		orderResponse.setPageNumber(pageOrders.getNumber());
		orderResponse.setPageSize(pageOrders.getSize());
		orderResponse.setTotalElements(pageOrders.getTotalElements());
		orderResponse.setTotalPages(pageOrders.getTotalPages());
		orderResponse.setLastPage(pageOrders.isLast());
		
		return orderResponse;
	}

	@Override
	public OrderDTO updateOrder(String emailId, Long orderId, String orderStatus) {

		Order order = orderRepo.findOrderByEmailAndOrderId(emailId, orderId);

		if (order == null) {
			throw new ResourceNotFoundException("Order", "orderId", orderId);
		}

		order.setOrderStatus(orderStatus);

		return modelMapper.map(order, OrderDTO.class);
	}
}

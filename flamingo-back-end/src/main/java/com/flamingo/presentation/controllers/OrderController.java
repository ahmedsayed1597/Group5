package com.flamingo.presentation.controllers;

import java.util.List;

import com.flamingo.presentation.dto.OrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.flamingo.buisness.services.interfaces.OrderService;
import com.flamingo.config.AppConstants;
import com.flamingo.presentation.dto.OrderDTO;
import com.flamingo.presentation.responseviewmodel.OrderResponse;

@RestController
@RequestMapping("/api")

// @SecurityRequirement(name = "E-Commerce Application")
public class OrderController {

    @Autowired
	public OrderService orderService;



    @GetMapping("/admin/orders")
	public ResponseEntity<OrderResponse> getAllOrders(
			@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_ORDERS_BY, required = false) String sortBy,
			@RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
		
		OrderResponse orderResponse = orderService.getAllOrders(pageNumber, pageSize, sortBy, sortOrder);

		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}

    @GetMapping("public/users/{emailId}/orders")
	public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable String emailId) {
		List<OrderDTO> orders = orderService.getOrdersByUser(emailId);
		
		return new ResponseEntity<List<OrderDTO>>(orders, HttpStatus.OK);
	}

    @GetMapping("public/users/{emailId}/orders/{orderId}")
	public ResponseEntity<OrderDTO> getOrderByUser(@PathVariable String emailId, @PathVariable Long orderId) {
		OrderDTO order = orderService.getOrder(emailId, orderId);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.OK);
	}
	
	@PutMapping("admin/users/{emailId}/orders/{orderId}/orderStatus/{orderStatus}")
	public ResponseEntity<OrderDTO> updateOrderByUser(@PathVariable String emailId, @PathVariable Long orderId, @PathVariable String orderStatus) {
		OrderDTO order = orderService.updateOrder(emailId, orderId, orderStatus);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.OK);
	}



    @PostMapping("/public/users/{emailId}/carts/{cartId}/order")
	public ResponseEntity<OrderDTO> orderProducts(@PathVariable String emailId, @PathVariable Long cartId) {
		OrderDTO order = orderService.placeOrder(emailId, cartId);
		
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
    
}

	@PostMapping("/public/users/{emailId}/order")
	public ResponseEntity<OrderDTO> orderProduct(@PathVariable String emailId, @PathVariable Long cartId) {
		OrderDTO order = orderService.placeOrder(emailId, cartId);

		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);

	}

	@PostMapping("/public/users/{emailId}/orderOne")
	public ResponseEntity<OrderRequestDTO> orderProduct(@PathVariable String emailId, @RequestBody OrderRequestDTO orderRequestDTO) {


		OrderRequestDTO order = orderService.placeOrder(emailId, orderRequestDTO);

		return new ResponseEntity<OrderRequestDTO>(order, HttpStatus.CREATED);

	}


}

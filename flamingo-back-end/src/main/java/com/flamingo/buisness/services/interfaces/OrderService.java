package com.flamingo.buisness.services.interfaces;


import java.util.List;

import com.flamingo.presentation.dto.OrderDTO;
import com.flamingo.presentation.dto.OrderRequestDTO;
import com.flamingo.presentation.responseviewmodel.OrderResponse;



public interface OrderService  {
	OrderDTO placeOrder(String emailId, Long cartId);
	OrderRequestDTO placeOrder(String emailId, OrderRequestDTO orderRequestDTO);

	OrderDTO getOrder(String emailId, Long orderId);
	
	List<OrderDTO> getOrdersByUser(String emailId);
	
	OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	OrderDTO updateOrder(String emailId, Long orderId, String orderStatus);
}
    


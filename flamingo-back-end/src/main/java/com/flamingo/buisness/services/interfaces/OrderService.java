package com.flamingo.buisness.services.interfaces;


import java.util.List;

import com.flamingo.presentation.dto.OrderDTO;
import com.flamingo.presentation.responseviewmodel.OrderResponse;



public interface OrderService  {
	OrderDTO placeOrder(String emailId, Long cartId, String paymentMethod);
	
	OrderDTO getOrder(String emailId, Long orderId);
	
	List<OrderDTO> getOrdersByUser(String emailId);
	
	OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
	
	OrderDTO updateOrder(String emailId, Long orderId, String orderStatus);
}
    


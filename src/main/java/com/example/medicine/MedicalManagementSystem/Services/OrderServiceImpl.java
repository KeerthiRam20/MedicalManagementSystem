package com.example.medicine.MedicalManagementSystem.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicine.MedicalManagementSystem.Entity.Cart;
import com.example.medicine.MedicalManagementSystem.Entity.Order;
import com.example.medicine.MedicalManagementSystem.Entity.Product;
import com.example.medicine.MedicalManagementSystem.repository.CartJpaRepository;
import com.example.medicine.MedicalManagementSystem.repository.OrderJpaRepository;
import com.example.medicine.MedicalManagementSystem.repository.ProductJpaRepository;



@Service
public class OrderServiceImpl implements OrderService {
	
	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	ProductJpaRepository productRepository;

	@Autowired
	CartJpaRepository cartRepository;

	@Autowired
	OrderJpaRepository orderRepository;

	/**
	 * This method is used to place order.
	 */

	@Override
	public Order placeOrder(int userId, String deliveryAddress, boolean payment) {
		logger.info("place order method called");
		List<Cart> cartDetails = cartRepository.findByUserId(userId);
		double totalPrice = cartDetails.stream().map(p -> p.getTotalAmount()).reduce((p, q) -> p + q).get();
		String com = " , ";
		String cartOfUser = cartDetails.stream().map(Object::toString).collect(Collectors.joining(com));
		String timeStamp = LocalDate.now().toString();
		String status = "";
		if (payment) {
			status = "Order Sucessfull";
		} else {
			status = "Payment Pending";
		}
		Order ord = new Order(userId, timeStamp, deliveryAddress, cartOfUser, totalPrice, payment, status);
		orderRepository.save(ord);
		for (Cart c : cartDetails) {
			cartRepository.deleteById(c.getCartId());
			Product prd = productRepository.findById(c.getProductId()).get();
			int quantity = c.getQuantity();
			prd.setQuantity(prd.getQuantity() - quantity);
			productRepository.save(prd);
		}
		return ord;
	}

	/**
	 * This method is used to get Order by User ID.
	 * 
	 * @param userId. Accepts UserID.
	 * @return Oder details of given User ID.
	 */

	public List<Order> getOrderByUserId(int userId) {
		logger.info("get order by user ID method called");
		return orderRepository.findByUserId(userId);
	}

	/**
	 * This method is used to get all orders.
	 * 
	 * @return list of all Orders.
	 */

	public List<Order> getAllOrders() {
		logger.info("Get all orders method called");
		List<Order> order = new ArrayList<Order>();
		orderRepository.findAll().forEach(u1 -> order.add(u1));
		return order;
	}

}

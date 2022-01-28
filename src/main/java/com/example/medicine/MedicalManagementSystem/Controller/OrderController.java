package com.example.medicine.MedicalManagementSystem.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicine.MedicalManagementSystem.Entity.Order;
import com.example.medicine.MedicalManagementSystem.Exception.USERException;
import com.example.medicine.MedicalManagementSystem.Services.OrderServiceImpl;


@CrossOrigin("*")
@RestController
public class OrderController {

	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderServiceImpl orderService;

	/**
	 * This method is used to place order.
	 * 
	 * @param userId          First parameter for the method. Accepts user ID.
	 * @param deliveryAddress Second parameter for the method. Accepts delivery
	 *                        address.
	 * @param payment         Third parameter for the method. Accepts Payment.
	 * @return returns order is placed successfully or not.
	 * @throws Exception if details to place order are wrong.
	 */

	@PostMapping("/User/PlaceOrder")
	public Order placeOrder(@Valid @RequestParam int userId, @Valid @RequestParam String deliveryAddress,
			@Valid @RequestParam boolean payment) throws Exception {
		if (userId > 0 && deliveryAddress != null && deliveryAddress != "") {
			logger.info("Order Placed Sucessfully");
			return orderService.placeOrder(userId, deliveryAddress, payment);
		} else {
			logger.error("Order not placed! Please Check your details to place order");
			throw new USERException("Error Occured!!! Please Check your details to place order");
		}
	}

	/**
	 * This method is used to View Order.
	 * 
	 * @param userId First parameter for the method. Accepts user ID
	 * @return Order details.
	 * @throws Exception if details are not entered correctly.
	 */

	@GetMapping("/User/ViewMyOrders")
	public List<Order> vewMyOrder(@RequestParam("userId") int userId) throws Exception {
		if (userId > 0) {
			logger.info("Order fetched for given User ID");
			return orderService.getOrderByUserId(userId);
		} else {
			logger.error("Cannot fetch Order! Please Check userId to proceed");
			throw new USERException("Error Occured!!!Please Check userId to proceed");
		}
	}

	/**
	 * This method is used to View all orders.
	 * 
	 * @return List of all orders.
	 */

	@GetMapping("/Admin/ViewAllOrders")
	public List<Order> getAllOrders() {
		logger.info("Fetched All orders");
		return orderService.getAllOrders();
	}

}

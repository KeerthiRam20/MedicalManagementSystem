package com.example.medicine.MedicalManagementSystem.Services;

import com.example.medicine.MedicalManagementSystem.Entity.Order;

public interface OrderService {
	
	Order placeOrder(int userId, String deliveryAddress, boolean payment);

}

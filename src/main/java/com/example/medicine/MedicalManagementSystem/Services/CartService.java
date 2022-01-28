package com.example.medicine.MedicalManagementSystem.Services;

import com.example.medicine.MedicalManagementSystem.Entity.Cart;

public interface CartService {
	
	Cart addToCart(int userid, int productId, int quantity);
	Cart removeFromCart(int userid, int productId, int quantity);
}

package com.example.medicine.MedicalManagementSystem.Services;

import java.util.List;

import com.example.medicine.MedicalManagementSystem.Entity.Product;



public interface ProductService {

	List<Product> getAllProducts();

	String saveOrUpdate(Product product);
	
	Product updateProduct(Product product);
}

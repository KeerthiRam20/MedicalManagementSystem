package com.example.medicine.MedicalManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicine.MedicalManagementSystem.Entity.Product;
import com.example.medicine.MedicalManagementSystem.repository.ProductJpaRepository;



@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductJpaRepository productRepository;

	/**
	 * This method is used to save or update product.
	 */

	@Override
	public String saveOrUpdate(Product product) {
		logger.info("Save or update product method called");
		productRepository.save(product);
		return "Product Added";
	}

	/**
	 * This method is used to get all products.
	 */

	@Override
	public List<Product> getAllProducts() {
		logger.info("Get all products method called");
		List<Product> product = new ArrayList<Product>();
		productRepository.findAll().forEach(p1 -> product.add(p1));
		return product;
	}

	/**
	 * This method is used to get product by name.
	 * 
	 * @param name. Accepts product name.
	 * @return product details of given product name.
	 */

	public List<Product> getProductByName(String name) {
		logger.info("Get product by name method called");
		return productRepository.findByProductName(name);
	}

	/**
	 * This method is used to get product by category.
	 * 
	 * @param name. Accepts product category.
	 * @return product details of given product category.
	 */

	public List<Product> getProductByCategory(String category) {
		logger.info("Get product by category method called");
		return productRepository.findByCategory(category);
	}

	/**
	 * This method is used to update product details.
	 */

	public Product updateProduct(Product product) {
		logger.info("Update product method called");
		Product p = productRepository.save(product);
		return p;
	}
}

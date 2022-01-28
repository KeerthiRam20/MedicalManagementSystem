package com.example.medicine.MedicalManagementSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.medicine.MedicalManagementSystem.Entity.Product;



public interface ProductJpaRepository extends CrudRepository<Product, Integer> {

	List<Product> findByProductName(String name);

	List<Product> findByCategory(String category);


}

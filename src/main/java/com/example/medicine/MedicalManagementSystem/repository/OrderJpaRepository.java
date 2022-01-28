package com.example.medicine.MedicalManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicine.MedicalManagementSystem.Entity.Order;


public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

	List <Order> findByUserId(int userId);

}

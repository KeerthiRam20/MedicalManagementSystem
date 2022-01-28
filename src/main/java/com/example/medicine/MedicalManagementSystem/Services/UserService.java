package com.example.medicine.MedicalManagementSystem.Services;

import java.util.List;

import com.example.medicine.MedicalManagementSystem.Entity.User;


public interface UserService {

	String saveOrUpdate(User user);
	List<User> getAllUsers();
	User loginUser(int userId, String password);
	User findOneById(int userId);
}

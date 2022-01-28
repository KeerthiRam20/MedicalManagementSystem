package com.example.medicine.MedicalManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicine.MedicalManagementSystem.Entity.User;
import com.example.medicine.MedicalManagementSystem.repository.UserJpaRepository;





@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserJpaRepository userRepository;
	
	/**
	 * This method is used to save or update user.
	 */
	
	@Override
	public String saveOrUpdate(User user) {
		logger.info("save or updagte user method called");
		userRepository.save(user);
		return "User Registered";
	}
	
	/**
	 * This method is used to view all users.
	 */
	
	@Override
	public List<User> getAllUsers() {
		logger.info("Get all users method called");
		List<User> user = new ArrayList<User>();
		userRepository.findAll().forEach(u1 -> user.add(u1));
		return user;
	}
	
	/**
	 * This method is used for user login.
	 */
	
	@Override
	public User loginUser(int userId, String password) {
		logger.info("login user method called");
		return userRepository.loginUser(userId, password);
	}
	
	/**
	 * This method is used to find user by ID.
	 */
	
	@Override
	public User findOneById(int userId) {
		logger.info("find user by id method called");
		return userRepository.findByUserId(userId);
	}
}

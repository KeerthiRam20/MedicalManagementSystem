package com.example.medicine.MedicalManagementSystem.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicine.MedicalManagementSystem.Entity.User;
import com.example.medicine.MedicalManagementSystem.Exception.USERException;
import com.example.medicine.MedicalManagementSystem.Services.UserService;


@CrossOrigin("*")
@RestController
public class UserController {
	/**
	 * Logger initialized.
	 */
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Auto wiring of user service class. 
	 */
	@Autowired
	UserService userService;

	/**
	 * This method is used to view all user functions.
	 * 
	 * @param User First parameter for the method. Accepts user ID.
	 * @return all user functions.
	 */

	@GetMapping("/GetUserFunctions")
	public String userFunction() {
		return "1. /User/RegisterUser \n2. /User/login \n3. /getProducts \n4. /getProductByName \n5. /getProductsByCategory \n6. /User/AddToCart \n7. /User/RemoveFromCart \n8. /User/ViewCart \n9. /User/PlaceOrder \n10. /User/ViewMyOrders \n11. /User/Logout";
	}

	/**
	 * This method is used to register User.
	 * 
	 * @param user First parameter for the method. Accepts user details.
	 * @return User registered.
	 * @throws Exception if incorrect details are entered.
	 */

	@PostMapping("/User/RegisterUser")
	public final String saveUser(@Valid @RequestBody @PathVariable User user) throws Exception {
		if (user.getUserId() > 0) {
			if (user.getFirstName() != null && user.getLastName() != null && user.getAddress() != null
					&& user.getMailId() != null && user.getPassword() != null || user.getMobileNo() != null) {
				logger.info("User Added sucessfully");
//				return user.getUserId() + " " + userService.saveOrUpdate(user) + "\n" + "(/GetUserFunctions)";
				return userService.saveOrUpdate(user);
			} else {
				logger.error("Exception Occured!!! USER field has incorrect data");
				throw new USERException(
						" Exception Occured!!! INVALID Products Details!!!Please Check Product Details");
			}
		} else {
			logger.error("Exception Occured!!! USER field has incoreect data");
			throw new USERException(" Exception Occured!!! INVALID ID!!!Please Check UserId");
		}
	}

	/**
	 * This method is used to login User.
	 * 
	 * @param userId   First parameter for the method. Accepts user ID.
	 * @param password Second parameter for the method. Accepts password.
	 * @return User logged in successfully or not.
	 */

	@PostMapping("/User/login")
	public ResponseEntity<?> loginUser(@RequestParam("userId") int userId,
			@RequestParam("password") String password) {
		logger.info("User Login Method Started!");
		final User user = userService.loginUser(userId, password);
		if (user != null) {
			logger.info("User Logged in");
			return ResponseEntity.accepted().body(user);
		}
		logger.error("User Login Failed");
		return new ResponseEntity("Login Failed!!!", HttpStatus.BAD_REQUEST);

	}

	/**
	 * This method is used to view all Users.
	 * 
	 * @return list of all Users.
	 */

	@GetMapping("/Admin/getAllUsers")
	List<User> getAllUsers() {
		logger.info("Users Retrived");
		return userService.getAllUsers();
	}
	
//	@GetMapping(value = "/Admin/getAllUsers")
//	public ResponseEntity<User> getAllTrainee() {
//		return new ResponseEntity<User>(HttpStatus.OK);
//	}

	/**
	 * This method is used to get user by user ID.
	 * 
	 * @param userId First parameter for the method. Accepts user ID.
	 * @param user   Second parameter for the method. Fetch user class.
	 * @return User of same ID if present.
	 */

	@GetMapping("/Admin/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId, User user) {
		logger.info("User is fetched by user id!!!");
		if (user.getUserId() == userId) {
			return ResponseEntity.ok(userService.findOneById(userId));
		} else {
			logger.error("Cannot fetch the user with this User ID");
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * This method is used to logout User.
	 * 
	 * @return user logged out successfully.
	 */

	@GetMapping("/User/Logout")
	public String userLogout() {
		return "--------Logged Out--------";
	}
}
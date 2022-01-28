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

import com.example.medicine.MedicalManagementSystem.Entity.Cart;
import com.example.medicine.MedicalManagementSystem.Exception.APIException;
import com.example.medicine.MedicalManagementSystem.Services.CartServiceImpl;



@CrossOrigin("*")
@RestController
public class CartController {
	/**
	 * Loggers initialized.
	 */
	Logger logger = LoggerFactory.getLogger(CartController.class);
	
	/**
	 * Auto wiring of cart service class.
	 */
	@Autowired
	CartServiceImpl cartService;

	/**
	 * The method is used to add products to cart.
	 * 
	 * @param userId    First parameter for the method. Accepts user ID.
	 * @param productId Second parameter for the method. Accepts product ID.
	 * @param quantity  Third parameter for the method. Accepts Quantity of product.
	 * @return Returns the message " Product Added/Updated in Cart" if products are
	 *         added successfully else returns the message "Selected Quantity of
	 *         product not present" if number of quantity entered is not present in
	 *         the stock.
	 * @throws Exception is thrown if details are not entered correctly.
	 */
	@PostMapping("/User/AddToCart")
	public String addProduct(@Valid @RequestParam int userId,@Valid @RequestParam int productId,@Valid @RequestParam int quantity)
			throws Exception {
		final Cart crt = cartService.addToCart(userId, productId, quantity);
		if (userId > 0 && productId > 0 && quantity > 0) {
			if (crt != null) {

				logger.info("Product Added/Updated in Cart");
				return " Product Added/Updated in Cart";
			} else {
				return " Selected Quantity of product not present ";
			}
		} else {

			logger.error(" Exception Occured!!! Product cannot be loaded to cart. Please check details ");
			throw new APIException("Exception Occured!!! Product cannot be loaded to cart. Please check details");
		}
	}

	/**
	 * The method is used to remove products from cart.
	 * 
	 * @param userId    First parameter for the method. Accepts user ID.
	 * @param productId Second parameter for the method. Accepts product ID.
	 * @param quantity  Third parameter for the method. Accepts Quantity of product.
	 * @return Returns the message "Product removed from cart" if products are
	 *         removed successfully else returns the message "Entered Product not
	 *         found in cart" if entered product ID is not present in the cart.
	 * @throws Exception is thrown if details are not entered correctly.
	 */
	@PostMapping("/User/RemoveFromCart")
	public String removeProduct(@RequestParam int userId, @RequestParam int productId, @RequestParam int quantity)
			throws Exception {
		final Cart crt = cartService.removeFromCart(userId, productId, quantity);
		if (userId > 0 && productId > 0 && quantity > 0) {
			if (crt != null) {
				logger.info("Product removed in Cart");
				return "Product removed from cart";
			} else {
				logger.error("Entered Product not found in cart");
				return "Entered Product not found in cart";
			}
		} else {
			logger.error("Exception Occured!!! Product cannot be removed from cart. Please check details");
			throw new APIException("Exception Occured!!! Product cannot be removed from cart. Please check details");
		}
	}

	/**
	 * 
	 * @param userId parameter for the method. Accepts user ID.
	 * @return List of details in the cart are returned.
	 */
	@GetMapping("/User/ViewCart")
	public List<Cart> vewCartDetails(@RequestParam("userId") int userId) {
		logger.info("Cart is displayed");
		return cartService.getCartByUserId(userId);
	}

}

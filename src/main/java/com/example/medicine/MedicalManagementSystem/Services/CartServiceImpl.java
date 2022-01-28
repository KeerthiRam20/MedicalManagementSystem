package com.example.medicine.MedicalManagementSystem.Services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicine.MedicalManagementSystem.Entity.Cart;
import com.example.medicine.MedicalManagementSystem.Entity.Product;
import com.example.medicine.MedicalManagementSystem.repository.CartJpaRepository;
import com.example.medicine.MedicalManagementSystem.repository.ProductJpaRepository;



@Service
public class CartServiceImpl implements CartService {

	Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	CartJpaRepository cartRepository;

	@Autowired
	ProductJpaRepository productRepository;

	/**
	 * The method is used to add products to cart.
	 */

	@Override
	public Cart addToCart(int userid, int productId, int quantity) {
		logger.info("add to cart method called");
		List<Cart> cartDetails = cartRepository.findByUserId(userid);
		Product p = productRepository.findById(productId).get();
		String productName = p.getProductName();
		int presentQty = p.getQuantity();
		cartDetails.stream().filter(a -> a.getProductId() == productId).map(q -> q.getProductName());
		boolean check = cartDetails.stream().anyMatch(q -> q.getProductId() == productId);
		Cart c = new Cart();
		if (presentQty < quantity) {
			return null;
		} else if (check) {
			c = cartRepository.findByProductId(productId);
			c.setQuantity(c.getQuantity() + quantity);
			c.setTotalAmount((double) c.getQuantity() * p.getPrice());
			c.setProductName(productName);
		} else {
			double totalAmount = p.getPrice() * (double) quantity;
			c = new Cart(userid, productId, productName, quantity, totalAmount);
		}
		cartRepository.save(c);
		return c;
	}

	/**
	 * The method is used to remove products from cart.
	 */

	@Override
	public Cart removeFromCart(int userid, int productId, int quantity) {
		logger.info("remove from cart method called");
		List<Cart> cartDetails = cartRepository.findByUserId(userid);
		Product p = productRepository.findById(productId).get();
		boolean check = cartDetails.stream().anyMatch(r -> r.getProductId() == productId);
		Cart c = new Cart();
		if (check) {
			c = cartRepository.findByProductId(productId);
			c.setQuantity(c.getQuantity() - quantity);
			c.setTotalAmount((double) c.getQuantity() * p.getPrice());
		} else {
			return null;
		}
		cartRepository.save(c);
		return c;
	}

	/**
	 * This method is used to get cart by user ID.
	 * 
	 * @return list of carts with given user ID.
	 */

	public List<Cart> getCartByUserId(int userId) {
		logger.info("getCartByUserId method called");
		return cartRepository.findByUserId(userId);
	}

}

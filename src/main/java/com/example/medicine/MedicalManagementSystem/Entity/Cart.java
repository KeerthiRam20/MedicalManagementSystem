package com.example.medicine.MedicalManagementSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity

@Table(name = "CartDetails")
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;
	@NotNull
	@Column(name = "user_id")
	private int userId;
	@NotNull
	@Column(name= "product_id")
	private int productId;
	@Column(name= "product_name")
	private String productName;
	@NotNull
	@Column(name = "Quantity")
	private int quantity;
	@Column(name = "Total_Amount")
	private double totalAmount;
	
	public Cart() {
		super();
	}
	public Cart( int userid, int productId, String productName,  int quantity, double totalAmount) {
		super();
		this.userId = userid;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.totalAmount = totalAmount ;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getUserid() {
		return userId;
	}
	public void setUserid(int userid) {
		this.userId = userid;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userid=" + userId + ", productId=" + productId + ", quantity=" + quantity
				+ ", productName=" + productName + " totalAmount=" + totalAmount + "]";
	}
	
	
}

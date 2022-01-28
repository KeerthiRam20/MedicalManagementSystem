package com.example.medicine.MedicalManagementSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private int orderId;
	@NotNull
	@Column(name = "userId")
	private int userId;
	@Column(name = "orderDate")
	private String orderDate;
	@NotNull
	@Size(min = 2, message = "Must have atleast 2 characters")
	@Column(name = "deliveryAddress")
	private String deliveryAddress;
	@Column(name = "listOfProducts")
	private String listOfProducts;
	@Column(name = "totalAmount")
	private double totalAmount;
	@Column(name = "payment")
	boolean payment;
	@Column(name = "orderStatus")
	private String orderStatus;

	public Order() {
		super();
	}

	public Order(int userId, String orderDate, String deliveryAddress, String listOfProducts,
			double totalAmount, boolean payment, String orderStatus) {
		super();
		this.userId = userId;
		this.orderDate = orderDate;
		this.deliveryAddress = deliveryAddress;
		this.listOfProducts = listOfProducts;
		this.totalAmount = totalAmount;
		this.payment = payment;
		this.orderStatus = orderStatus;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(String listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", orderDate=" + orderDate + ", deliveryAddress="
				+ deliveryAddress + ", listOfProducts=" + listOfProducts + ", totalAmount=" + totalAmount + ", payment="
				+ payment + ", orderStatus=" + orderStatus + "]";
	}

	
}

package com.example.medicine.MedicalManagementSystem.service;

import com.example.medicine.MedicalManagementSystem.Entity.Order;
import com.example.medicine.MedicalManagementSystem.Services.OrderServiceImpl;
import com.example.medicine.MedicalManagementSystem.repository.OrderJpaRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private OrderServiceImpl orderService;

	@MockBean
	private OrderJpaRepository orderRepo;
	
	@Test
	public void testPlaceOrder() {
		Order o = new Order();
		o.setUserId(5);
		o.setOrderDate("Januray 3, 2021");
		o.setOrderId(1001);
		o.setListOfProducts("Vicks, Crocin");
		o.setDeliveryAddress("Mumbai");
		o.setTotalAmount(200);
		o.setPayment(true);
		o.setOrderStatus("processing");
		Mockito.when(orderRepo.save(o)).thenReturn(o);
		Assertions.assertNotNull(o);
	}
	

	
	@Test
	public void testGetAllOrders() {
		List<Order> list = new ArrayList<>();
		Mockito.when(orderRepo.findAll()).thenReturn(list);
		assertThat(orderService.getAllOrders()).isEqualTo(list);
	}
}

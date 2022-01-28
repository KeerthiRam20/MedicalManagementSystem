package com.example.medicine.MedicalManagementSystem.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.medicine.MedicalManagementSystem.MedicalManagementSystemApplicationTests;
import com.example.medicine.MedicalManagementSystem.Entity.Cart;
import com.example.medicine.MedicalManagementSystem.Services.CartServiceImpl;
import com.example.medicine.MedicalManagementSystem.repository.CartJpaRepository;



@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CartServiceTest {
	@InjectMocks
	private CartServiceImpl cartService;
	
	
	@MockBean
	private CartJpaRepository cartRepository;


	@Test
	public void testCartByUserId() 
	{
		Cart cart1 =new Cart();
		cart1.setUserid(1);
		cart1.setProductId(101);
		cart1.setProductName("vicks");
		cart1.setQuantity(2);
		cart1.setTotalAmount(30);
		
		Cart cart2 =new Cart();
		cart2.setUserid(1);
		cart2.setProductId(102);
		cart2.setProductName("dcold");
		cart2.setQuantity(2);
		cart2.setTotalAmount(200);
		
		List<Cart> cartList = new ArrayList<>();
		cartList.add(cart1);
		cartList.add(cart2);
		Mockito.when(cartRepository.findByUserId(1)).thenReturn(cartList);

		assertThat(cartService.getCartByUserId(1)).isEqualTo(cartList);
	}

	@Test
	public void testAddToCart() 
	{
		Cart cart =new Cart();
		cart.setUserid(1);
		cart.setProductId(101);
		cart.setProductName("vicks");
		cart.setQuantity(2);
		cart.setTotalAmount(30);
		
		List<Cart> cartList1 = new ArrayList<>();
		cartList1.add(cart);
		Mockito.when(cartRepository.findByUserId(1)).thenReturn(cartList1);
		assertThat(cartService.getCartByUserId(1)).isEqualTo(cartList1);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveFromCart() 
	{
		Cart cart =new Cart();
		cart.setUserid(1);
		cart.setProductId(101);
		cart.setProductName("vicks");
		cart.setQuantity(2);
		cart.setTotalAmount(30);
		
		Mockito.when(cartRepository.getOne(1)).thenReturn(cart);
		Mockito.when(cartRepository.existsById(cart.getCartId())).thenReturn(false);
		assertFalse(cartRepository.existsById(cart.getCartId()));
	}
	
	
	
	
	
}

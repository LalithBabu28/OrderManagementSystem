package com.NewProject;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class OrderController {
	
	
	private OrderService orderservice;
	
	OrderController(OrderService orderservice)
	{
		this.orderservice=orderservice;
	}
	
	@PostMapping("/addOrder")
	public void AddOrder(@RequestBody OrderDetails orderdetails)
	{
		
		orderservice.AddItem(orderdetails);
	}
	
	@GetMapping("/getall")
	public List<OrderDetails> getall()
	{
		return orderservice.getAll();
	}
	
	

}

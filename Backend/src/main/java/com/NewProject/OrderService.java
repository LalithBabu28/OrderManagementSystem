package com.NewProject;

import org.springframework.stereotype.Service;
import java.util.*;

@Service 
public class OrderService {
	
	
	private OrderRepository orderrepo;
	
	OrderService(OrderRepository orderrepo)
	{
		this.orderrepo=orderrepo;
	}
	
	
	public void AddItem(OrderDetails orderdetails)
	{
		try {
		orderrepo.AddOrder(orderdetails);
	}
		catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	public List<OrderDetails> getAll()
	{
		return orderrepo.getAll();
	}

}

package com.NewProject;
import java.util.*;

public class OrderDetails {
	
	private String orderid;
	

	private String customername;
	private float price;
    private String status;
    private Map<String,Integer> orderitems;
	public OrderDetails() {
		
	}
	
	public OrderDetails(String orderid, String customername, float price,String status,Map<String,Integer> orderitems) {
		this.orderid = orderid;
		this.customername = customername;
		this.price= price;
		this.status=status;
		this.orderitems=orderitems;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public void updateStatus(String status) {
	      this.status=status;
	}

	public Map<String, Integer> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Map<String, Integer> orderitems) {
		this.orderitems = orderitems;
	}
	
	

	
}

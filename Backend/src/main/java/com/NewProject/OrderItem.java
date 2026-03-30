package com.NewProject;

public class OrderItem {
	
	private String orderid;
	private String foodname;
	private int qty;
	
	public OrderItem() {
		
	}
	public OrderItem(String orderid,String foodname, int qty) {
		this.orderid=orderid;
		this.foodname = foodname;
		this.qty = qty;
	}
	
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
}

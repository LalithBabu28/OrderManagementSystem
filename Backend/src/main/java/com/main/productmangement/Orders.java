package com.main.productmangement;

import java.io.Serializable;

public class Orders implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productid;
	private Long qty;
	
	public Orders()
	{
		
	}
	public Orders(String productid,Long qty)
	{
		this.productid=productid;
		this.qty=qty;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	
	
	
	
}

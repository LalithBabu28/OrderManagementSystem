package com.main.productmangement;

public class Product {
	
	private String productid;
	private String productname;
	private Float price;
	private String Image;
	private Long qty;
	
	public Product(){
		
	}
	public Product(String productid, String productname, Float price, String Image,Long qty) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.price = price;
		this.Image = Image;
		this.qty=qty;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String Image) {
		this.Image = Image;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	

}

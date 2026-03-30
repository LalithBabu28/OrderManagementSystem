package com.main.productmangement;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
   
	
	private Services service;
	private OrderProducer ordproduce; 
	public ProductController(Services service,OrderProducer ordproduce)
	{
		this.service=service;
		this.ordproduce=ordproduce;
	}
	
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts()
	{
		return service.getAllpro();
	}
	
	@PutMapping("/addorders")
	public void adorders(@RequestBody List<Orders> order)
	{
		ordproduce.addorders(order);
	}
	@PostMapping("/Addelastic")
	public void Addelastic()
	{
		List<Product> prod=service.getAllpro();
		service.addelastic(prod);
		
	}
	
	@GetMapping("/GetElastic")
	public List<Product> GetElastic()
	{
		 return service.getelastic();
	}
	
	@GetMapping("/getbymatch")
	public List<Product> getbymatch(@RequestParam String field)
	{
		return service.getbymatch(field);
	}
	
	
	
}
	

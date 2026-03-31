package com.main.productmangement;


import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
   
	
	private Services service;
	private OrderProducer ordproduce; 
	private GoogleAuthService authService;
	public ProductController(Services service,OrderProducer ordproduce
			,GoogleAuthService authService)
	{
		this.service=service;
		this.ordproduce=ordproduce;
		this.authService=authService;
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
	
	@PostMapping("/auth/google")
	public ResponseEntity<?> authenticate(@RequestBody Map<String, String> body) {
        try {
            String token = body.get("token");
            GoogleIdToken.Payload payload = authService.verifyToken(token);
            return ResponseEntity.ok("User authenticated: " + payload.getEmail());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Invalid Token");
        }
    }
	
	
	
	
}
	

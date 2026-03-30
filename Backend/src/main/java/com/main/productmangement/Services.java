package com.main.productmangement;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class Services {
	
	private ProductRepository prorepo;
	private ElasticRepo esrepo;
	public Services(ProductRepository prorepo,ElasticRepo esrepo)
	{
		this.prorepo=prorepo;
		this.esrepo=esrepo;
	}
	
	public List<Product> getAllpro()
	{
	    return prorepo.getallpro();
	}
	
	public void addelastic(List<Product> prod){
		esrepo.addelastic(prod);
	}
	
	public List<Product> getelastic() {
		return esrepo.getelastic();
	}
	
    public List<Product> getbymatch(String str) {
		return esrepo.getbymatch(str);
	}
}

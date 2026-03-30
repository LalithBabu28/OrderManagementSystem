package com.main.productmangement;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ElasticRepo {
	
	private static final Logger log=LoggerFactory.getLogger(ElasticRepo.class);
	private ElasticsearchClient esclient;
	
	ElasticRepo(ElasticsearchClient estemplate)
	{
		this.esclient=estemplate;
	}
	
	public void addelastic(List<Product> pro)
	{
		try {
		for(Product p : pro) {
		IndexResponse response=esclient.index(i ->i.index("product_index").id(p.getProductid()).document(p));
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		
	}
	
	public List<Product> getelastic()
	{
		try {
		SearchResponse<Product> response=esclient.search(s -> s.index("product_index")
				.query(q -> q.matchAll(m -> m))
				.size(100),Product.class);
		return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
	}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Product> getbymatch(String fields)
	{
		SearchResponse<Product> response;
		try {
//		response=esclient.search(s -> s.index("product_index").query(q -> q.match(m -> m.field("productname").query(fields).fuzziness("Auto"))),Product.class);
		response=esclient.search(s -> s.index("product_index")
				.query(q -> q.wildcard(w -> w.field("productname").value("*" + fields.toLowerCase() + "*"))),Product.class);
		}
		catch(Exception e)
		{
			throw new  RuntimeException(e); 
		}
		
		return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
		
	}

}

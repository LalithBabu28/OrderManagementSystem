package com.main.productmangement;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
@PropertySource("classpath:sql.properties")
public class ProductRepository {
	
	private NamedParameterJdbcTemplate jdbctemplate;
	
	ProductRepository(NamedParameterJdbcTemplate jdbctemplate)
	{
		this.jdbctemplate=jdbctemplate;
	}
	
	@Value("${Product.getAll}")
	private String getall;
	
	@Value("${Product.order}")
	private String updateorder;
	
	@Value("${Product.getbyid}")
	private String getbyId;
	
	public List<Product> getallpro()
	{
		List<Product> allpro=jdbctemplate.query(getall,new BeanPropertyRowMapper<>(Product.class));
		return allpro;
	}
	
	public void placeorder(List<Orders> order)
	{
		for(Orders ord : order) {
		MapSqlParameterSource par=new MapSqlParameterSource()
				.addValue("productid", ord.getProductid());
		
		Product pro=jdbctemplate.queryForObject(getbyId,par,new BeanPropertyRowMapper<>(Product.class));
				
		MapSqlParameterSource param=new MapSqlParameterSource()
				.addValue("productid", ord.getProductid())
				.addValue("qty",(Long)(pro.getQty() -  ord.getQty()));
		
		jdbctemplate.update(updateorder,param);
		}
		
	}

}

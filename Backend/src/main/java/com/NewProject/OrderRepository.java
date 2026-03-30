package com.NewProject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;



@Repository
@PropertySource("classpath:sql.properties")
public class OrderRepository {
	
	@Value("${Order.Insert}")
	private String InsertOrder;
	
	@Value("${Order.GetOrder}")
	private String GetOrder;
	
	@Value("${Order.ItemsInsert}")
	private String InsertItems;
	
	@Value("${Order.ItemsGet}")
	private String GetItems;
	
	private NamedParameterJdbcTemplate jdbctemplate;
	
	public OrderRepository(NamedParameterJdbcTemplate jdbctemplate){
	this.jdbctemplate=jdbctemplate;
	}
	
	public void AddOrder(OrderDetails orderdetail)
	{
		
		MapSqlParameterSource param=new MapSqlParameterSource().addValue("orderid", orderdetail.getOrderid())
				.addValue("customername", orderdetail.getCustomername())
				.addValue("price", orderdetail.getPrice())
				.addValue("status", orderdetail.getStatus());
		
		jdbctemplate.update(InsertOrder, param);
		
		Map<String,Integer> map=orderdetail.getOrderitems();
		
		for(Map.Entry<String,Integer> v : map.entrySet())
		{
			MapSqlParameterSource params=new MapSqlParameterSource()
					.addValue("orderid", orderdetail.getOrderid())
					.addValue("foodname",v.getKey())
					.addValue("qty", v.getValue());
			
			jdbctemplate.update(InsertItems,params);
		}
		
	}
	
	public List<OrderDetails> getAll()
	{
		
		List<OrderDetails> orders=jdbctemplate.query(GetOrder,new BeanPropertyRowMapper<>(OrderDetails.class));
		
		for(OrderDetails or : orders)
		{
			MapSqlParameterSource param=new MapSqlParameterSource()
					.addValue("orderid", or.getOrderid());
			
			List<OrderItem> order=jdbctemplate.query(GetItems,param, new BeanPropertyRowMapper<>(OrderItem.class));
			
			
			Map<String,Integer> resmap=new HashMap<>();
			
			for(OrderItem ord :order)
			{
				if(ord.getOrderid().equals(or.getOrderid())) {
			    resmap.put(ord.getFoodname(),ord.getQty() );
			}}
			
			
			or.setOrderitems(resmap);
		}
		
		return orders;
	}
	
	
}

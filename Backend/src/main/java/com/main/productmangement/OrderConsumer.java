package com.main.productmangement;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import java.util.*;

import org.slf4j.*;

@Service
public class OrderConsumer {
	
	private ProductRepository prorepo;
	private static final Logger log=LoggerFactory.getLogger(OrderConsumer.class);
	
	public OrderConsumer(ProductRepository prorepo)
	{
		this.prorepo=prorepo;
	}

	@JmsListener( destination = "ordermanaging")
	public void consumer(List<Orders> order)
	{
		try {
		prorepo.placeorder(order);
		log.info("Received by consumer");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

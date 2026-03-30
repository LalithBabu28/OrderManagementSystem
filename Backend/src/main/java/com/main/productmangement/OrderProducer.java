package com.main.productmangement;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrderProducer {
	
	private JmsTemplate jmstemplate;
	
	private static final String Queuename="ordermanaging";
	
	OrderProducer(JmsTemplate jmstemplate)
	{
		this.jmstemplate=jmstemplate;
	}
	public void addorders(List<Orders>  order)
	{
		jmstemplate.convertAndSend(Queuename,order);
	}

}

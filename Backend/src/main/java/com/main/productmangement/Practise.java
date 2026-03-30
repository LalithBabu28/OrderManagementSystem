package com.main.productmangement;

import org.slf4j.*;


public class Practise {
	private static final Logger log=LoggerFactory.getLogger(Practise.class);
	public static String fun()
	{
	String str="this is the Ball of the HIT in the,banned on it.";
		
	String correct=str.replaceAll("[.,]"," ");
	return  correct.toLowerCase();
	}
	public static void main(String[] args)
	{
		log.info(fun());
	}	
}

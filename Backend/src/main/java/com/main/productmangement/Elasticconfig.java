package com.main.productmangement;



import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class Elasticconfig extends ElasticsearchConfiguration{
	
	
	@Value("${spring.elasticsearch.username}")
	private String username;
	@Value("${spring.elasticsearch.password}")
	private String password;
	
	@Override
	public ClientConfiguration clientConfiguration()
	{
		return  ClientConfiguration.builder()
				.connectedTo("localhost:9200")
				 .usingSsl(sslcontext())
				.withBasicAuth(username,password)
				.build();
	}
	
	private static  SSLContext sslcontext()
	{
		try {
		   return new  SSLContextBuilder().loadTrustMaterial(null,TrustAllStrategy.INSTANCE).build();
	     }
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	

}

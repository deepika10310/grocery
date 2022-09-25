package com.grocery.on.wheels.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="*")
public class GroceryPropertyConfig {

	/*
	 * @Autowired private Environment env;
	 * 
	 * public String getProperty(String propKey) { return env.getProperty(propKey);
	 * }
	 */

	@Value("${s3.domain}")
	public String s3Domain;

	public String getS3Domain() {
		System.out.println("get " + s3Domain);
		return s3Domain;
	}

	
	public void setS3Domain(String s3Domain) {
		System.out.println("set " + s3Domain);
		this.s3Domain = s3Domain;
	}

}

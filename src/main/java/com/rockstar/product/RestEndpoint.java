package com.rockstar.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RestEndpoint {

	public static void main(String[] args) {
		SpringApplication.run(RestEndpoint.class, args);
	}
}
package com.company.librarycrudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LibraryCrudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryCrudServiceApplication.class, args);
	}

}

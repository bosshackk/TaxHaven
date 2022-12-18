package com.quad.TaxHaven;

import com.quad.TaxHaven.domain.user.Client;
import com.quad.TaxHaven.domain.user.User;
import com.quad.TaxHaven.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxHavenApplication {

	@Autowired
	ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaxHavenApplication.class, args);
		System.out.println("Sri Ganesh");
	}

}

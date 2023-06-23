package com.consumo.api;

import com.consumo.api.client.JwtClient;
import com.consumo.api.model.dto.UsersDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {

		JwtClient jwtClient = new JwtClient();
		UsersDTO dto = new UsersDTO();
		dto.setUsername("dan");
		dto.setPassword("dan123");
		jwtClient.sendAuthenticationRequest(dto);

		SpringApplication.run(ApiApplication.class, args);
	}

}

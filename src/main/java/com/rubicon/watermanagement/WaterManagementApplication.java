package com.rubicon.watermanagement;

import com.rubicon.watermanagement.controller.HomeController;
import com.rubicon.watermanagement.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;

@SpringBootApplication
public class WaterManagementApplication implements CommandLineRunner {
@Autowired
private HomeController homeController;
	public static void main(String[] args) {
		SpringApplication.run(WaterManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LocalDateTime localDateTime=LocalDateTime.now().plusDays(10);

		OrderRequest orderRequest=new OrderRequest();
		orderRequest.setFarmid("12345678");
		orderRequest.setDuration("02");
		orderRequest.setDateTime(localDateTime);
		homeController.createOrder(orderRequest);
	}
}

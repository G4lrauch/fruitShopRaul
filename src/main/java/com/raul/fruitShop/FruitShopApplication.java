package com.raul.fruitShop;

import com.raul.fruitShop.services.FruitShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FruitShopApplication {

	@Autowired
	private FruitShopService service;

	public static void main(String[] args) {
		SpringApplication.run(FruitShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				service.printTicketOfPurchase();
			}
		};
	}

}

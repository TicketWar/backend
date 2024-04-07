package com.ticketwar.ticketwar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TicketwarApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketwarApplication.class, args);
	}

}

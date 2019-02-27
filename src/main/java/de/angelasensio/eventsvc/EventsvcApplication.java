package de.angelasensio.eventsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventsvcApplication {

	public static void main(String[] args) {
		System.setProperty("spring.datasource.url","jdbc:h2:tcp://localhost:9091/mem:employeedb");
		SpringApplication.run(EventsvcApplication.class, args);
	}



}

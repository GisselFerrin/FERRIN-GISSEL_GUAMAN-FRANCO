package com.example.ParcialFinal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ParcialFinalApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParcialFinalApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(ParcialFinalApplication.class, args);
		LOGGER.info("Turnos Clinica is now running...");
	}

}

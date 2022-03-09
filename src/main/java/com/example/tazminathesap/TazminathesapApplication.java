package com.example.tazminathesap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class TazminathesapApplication {

	private static final Logger logger = LoggerFactory.getLogger(TazminathesapApplication.class);
	
	public static void main(String[] args) {
		
		logger.info("Aplikasyon başladı.");
		SpringApplication.run(TazminathesapApplication.class, args);
	}

}

package com.raphau.temp_stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TempStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempStreamApplication.class, args);
	}

}

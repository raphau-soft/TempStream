package com.raphau.temp_stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TempStreamProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempStreamProducerApplication.class, args);
	}

}

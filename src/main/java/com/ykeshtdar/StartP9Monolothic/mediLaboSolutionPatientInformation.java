package com.ykeshtdar.StartP9Monolothic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@SpringBootApplication
public class mediLaboSolutionPatientInformation {

	public static void main(String[] args) {
		SpringApplication.run(mediLaboSolutionPatientInformation.class, args);

	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}

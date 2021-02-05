package com.elkexample.elkserviceapplication;


import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class ElkserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ElkserviceApplication.class, args);
		System.out.println("Current Directory = " + System.getProperty("user.dir"));

	}
}



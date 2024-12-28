package com.project.wma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.project.wma.*"})
public class WmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmaApplication.class, args);
	}

}

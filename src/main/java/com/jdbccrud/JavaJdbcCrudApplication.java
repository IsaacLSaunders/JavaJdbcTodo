package com.jdbccrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class JavaJdbcCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaJdbcCrudApplication.class, args);
	}

}

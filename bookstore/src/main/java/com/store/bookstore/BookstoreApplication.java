package com.store.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.store.bookstore")
public class BookstoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookstoreApplication.class, args);
	}

}

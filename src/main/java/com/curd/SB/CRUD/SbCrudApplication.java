package com.curd.SB.CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping(path = "/")
@RestController
public class SbCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbCrudApplication.class, args);
	}

	@GetMapping("/")
	public String helloWorld(){
		return "Hello World";
	}
}

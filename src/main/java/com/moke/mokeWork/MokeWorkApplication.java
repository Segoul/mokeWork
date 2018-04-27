package com.moke.mokeWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MokeWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MokeWorkApplication.class, args);
	}
}

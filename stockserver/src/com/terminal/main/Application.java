package com.terminal.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application 
{
	public static void main(String[] args) 
	{
		// Start spring application server
		SpringApplication.run(Application.class,args);
	}
}
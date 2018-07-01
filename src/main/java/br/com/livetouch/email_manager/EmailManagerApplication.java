package br.com.livetouch.email_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class EmailManagerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EmailManagerApplication.class, args);
	}
}

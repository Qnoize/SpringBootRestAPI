package ru.jmentor.front.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = "ru.jmentor.front")
public class SpringBootExampleApplication {

	public static void main(String[] args) { SpringApplication.run(SpringBootExampleApplication.class, args); }

}

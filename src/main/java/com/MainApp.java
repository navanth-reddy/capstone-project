package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Importing the Spring Boot annotation to enable auto-configuration and component scanning.

* This annotation is a shortcut that includes @Configuration, @EnableAutoConfiguration, and @ComponentScan.
* @Configuration → Marks this class as a configuration class.

* @EnableAutoConfiguration → Automatically configures Spring Boot based on dependencies.

* @ComponentScan → Scans for Spring components (like @RestController, @Service, etc.) in the package and its sub-packages.
* 
* Runs the Spring Boot application and starts the embedded web server (we are using Tomcat).
    
*/
@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

}
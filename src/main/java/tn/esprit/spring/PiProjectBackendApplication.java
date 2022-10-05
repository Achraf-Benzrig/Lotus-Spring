package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@Configuration
@SpringBootApplication
public class PiProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiProjectBackendApplication.class, args);
	}

}

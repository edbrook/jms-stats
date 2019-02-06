package uk.co.dekoorb.jmsstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JmsStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsStatsApplication.class, args);
	}

}


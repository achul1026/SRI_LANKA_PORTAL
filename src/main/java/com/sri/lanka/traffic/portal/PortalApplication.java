package com.sri.lanka.traffic.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@PropertySource(value = "classpath:application-dev.yml",ignoreResourceNotFound = true)
public class PortalApplication {

	public static void main(String[] args) {
//		SpringApplication.run(PortalApplication.class, args);
        SpringApplication app = new SpringApplication(PortalApplication.class);
        // Set default to 'prod' if no other profile is set
        /*String[] activeProfiles = System.getProperty("spring.profiles.active", "prod").split(",");
        app.setAdditionalProfiles(activeProfiles);*/
        app.run(args);
	}

}

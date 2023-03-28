package com.una.carassistschedulebackend;

import com.una.carassistschedulebackend.persistence.DynamoDbConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@Import({DynamoDbConfig.class })
@OpenAPIDefinition(info = @Info(title = "Car Assist Schedule", version = "1.0", description = "Car Assist Schedule API",
	license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
	contact = @Contact(name = "Koala's Software Support", email = "henrique.wallace@hotmail.com"),
	termsOfService = "http://koalassoftware.com/use_terms_api")
)
public class CarAssistScheduleBackendApplication {

	public static final Logger log = LoggerFactory.getLogger(CarAssistScheduleBackendApplication.class);
	public static void main(String[] args) {
		log.info("Initializing...");
		System.setProperty("server.servlet.context-path", "/car-schedule-api");
		new SpringApplicationBuilder(CarAssistScheduleBackendApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}

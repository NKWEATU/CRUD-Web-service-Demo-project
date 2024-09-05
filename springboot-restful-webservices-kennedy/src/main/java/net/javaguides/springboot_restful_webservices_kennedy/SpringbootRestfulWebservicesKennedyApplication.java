package net.javaguides.springboot_restful_webservices_kennedy;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring REST API Documentation",
				description = "Spring REST API Documentation",
				version = "version 1.0.0",
				contact = @Contact(
						name = "Nkweatu Kennedy",
						email = "nkweatukennedyjavablog@gmail.com",
						url   ="http://www.nkweatukennedyjavablog.net"
		),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.nkweatukennedyjavablog.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Spring Boot User Management Documentation",
				url = "http://www.nkweatukennedyjavablog.net/user_management.html"
		)
)
public class SpringbootRestfulWebservicesKennedyApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesKennedyApplication.class, args);
	}
}

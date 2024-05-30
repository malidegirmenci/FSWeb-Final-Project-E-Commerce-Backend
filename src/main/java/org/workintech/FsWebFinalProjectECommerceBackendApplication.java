package org.workintech;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Ecommerce",
				version = "1.0.0",
				description = "This project about a ecommerce",
				contact = @Contact(
						name = "Mehmet Ali Değirmenci",
						email = "m.alidegirmenci@hotmail.com"
				),
				license = @License(
						name = "licence",
						url = "madtech"
				)

		)
)
public class FsWebFinalProjectECommerceBackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FsWebFinalProjectECommerceBackendApplication.class);
		//app.setAdditionalProfiles("dev");
		app.setAdditionalProfiles("prod");
		app.run(args);
		//SpringApplication.run(FsWebFinalProjectECommerceBackendApplication.class, args);
	}

}

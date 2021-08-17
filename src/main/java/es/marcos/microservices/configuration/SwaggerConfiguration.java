package es.marcos.microservices.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean // para que se lance en el arranque de la aplicacion
	public Docket api() {
		// la url del html es: http://localhost:8080/swagger-ui.html
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfo("Marcos Puerto Barrero", "Microservice", "1.0",
						"https://www.instagram.com/marcospuertobarrero14",
						new Contact("Marcos", "https://www.instagram.com/marcospuertobarrero14", "marcospuertobarrero1@gmail.com"), "Linkedin",
						"https://www.linkedin.com/in/marcos-puerto-barrero-4285031a1/", Collections.emptyList()));
	}
}

package es.marcos.microservices.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@Configuration
@Data
@ToString
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {
	
	private String autor;

	private int edad;

	private String edicion;
}

package es.marcos.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.marcos.microservices.configuration.ApplicationConfig;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("HolaMundo")
@Api(tags = "Service for Print Hola Mundo")
public class HolaMundoRest {

	@Autowired
	private ApplicationConfig applicationConfig;

	@GetMapping
	public String saludar() {
		String autor = " Autor: " + applicationConfig.getAutor();
		String edad = ", Edad: " + applicationConfig.getEdad();
		String edicion = ", Edicion: " + applicationConfig.getEdicion();
		String cadena = "Hola Mundo Servicio Rest Java," + autor + edad + edicion;
		System.out.println(cadena);
		System.out.println(applicationConfig.toString());
		
		return cadena;
	}
}

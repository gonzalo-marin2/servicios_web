package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Formacion;
import service.interfaces.FormacionService;

@RestController
public class FormacionController {
	@Autowired
	FormacionService service;
	
	@GetMapping(value="catalogo",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Formacion> catalogo(){
		return service.catalogo();
	}
	
	@GetMapping(value="catalogo/{max}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Formacion> catalogo(@PathVariable("max") int max){
		return service.catalogoPorDuracionMax(max);
	}
	
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Formacion formacion) {
		service.alta(formacion);
	}
}

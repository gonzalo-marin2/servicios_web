package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import init.model.Vehiculo;
import init.service.interfaces.VehiculosService;

@CrossOrigin("*")//permitimos llamadas desde cualquier origen
@RestController
public class VehiculosController {

	@Autowired
	VehiculosService service;
	
	@GetMapping(value="buscarPrecioMax/{precioMax}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Vehiculo> buscarPorPrecioMax(@PathVariable("precioMax") double precioMax){
		return service.buscarPorPrecioMax(precioMax);
	}
	
	@GetMapping(value="buscarPorRangoKilometros/{minimo}/{maximo}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Vehiculo> buscarPorRangoKilometros(@PathVariable("minimo") int minimo, @PathVariable("maximo") int maximo){
		return service.buscarPorRangoKilometros(minimo, maximo);
	}
	
}

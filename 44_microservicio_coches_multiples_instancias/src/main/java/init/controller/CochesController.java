package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Coche;
import init.service.interfaces.CochesService;

@CrossOrigin("*")//permitimos llamadas desde cualquier origen
@RestController
public class CochesController {
	
	//para imprimir el nombre de la instancia que soy
	@Value("#{eureka.instance.instance-id}")
	private String instanciaId;
	
	@Autowired
	CochesService service;
	
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Coche coche) {
		service.alta(coche);
	}
	
	@PutMapping(value="actualizar",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Coche coche) {
		service.actualizarCoche(coche);
	}
	
	@DeleteMapping(value="eliminar/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Coche eliminar(@PathVariable("matricula") String matricula) {
		return service.eliminar(matricula);
	}
	
	@GetMapping(value="catalogo",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> catalogo(){
		//para imprimir el nombre de la instancia que soy
		System.out.println("Ejecutándose instancia: "+instanciaId);
		return service.catalogo();
	}
	
	@GetMapping(value="buscarMarca/{marca}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> cochesPorMarca(@PathVariable("marca") String marca){
		return service.cochesPorMarca(marca);
	}
	
	
	@GetMapping(value="buscarPrecioMax/{precioMax}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> cochesPorPrecioMax(@PathVariable("precioMax") double precioMax){
		return service.cochesPorPrecioMax(precioMax);
	}
	
	
}

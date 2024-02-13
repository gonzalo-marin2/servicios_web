package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin("*")//permitimos llamadas desde cualquier origen
@RestController
public class CochesController {
	@Autowired
	CochesService service;
	
	@Operation(summary = "Alta de nuevos coches", description = "Recibe como parámetro un JSON con los datos del nuevo coche. Solo le da de alta sino existe la matrícula")
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void alta(@Parameter(description = "Objeto JSON con los datos del coche") @RequestBody Coche coche) {
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
		return service.catalogo();
	}
	
	@Operation(summary = "Búsqueda por marca", description = "Devuelve un JSON con los datos de los coches cuya marca se recibe como parámetro")
	@GetMapping(value="buscarMarca/{marca}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> cochesPorMarca(@Parameter(description = "Marca de los coches buscados") @PathVariable("marca") String marca){
		return service.cochesPorMarca(marca);
	}
	
	
	@GetMapping(value="buscarPrecioMax/{precioMax}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> cochesPorPrecioMax(@PathVariable("precioMax") double precioMax){
		return service.cochesPorPrecioMax(precioMax);
	}
	
	
}

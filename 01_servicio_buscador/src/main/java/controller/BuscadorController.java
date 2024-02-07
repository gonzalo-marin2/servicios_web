package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Resultado;
import service.interfaces.BuscadorService;

@CrossOrigin("*")//permitimos llamadas desde cualquier origen
@RestController
public class BuscadorController {
	@Autowired
	BuscadorService buscadorService;
	
	@GetMapping(value="buscar",produces = "application/json")
	public List<Resultado> buscar(@RequestParam("tematica") String tematica) {
		return buscadorService.buscar(tematica);
	}
	
	@PostMapping(value="alta",consumes="application/json")
	//consumes para decir que utiliza json
	public void alta(@RequestBody Resultado resultado) {
		//@requestbody es para que el cliente envíe un json
		buscadorService.agregar(resultado);
	}
	
	@DeleteMapping(value="eliminar",produces="application/json")
	public List<Resultado> eliminar(@RequestParam("url") String url){
		return buscadorService.eliminarResultado(url);
	}
	
	
	@PutMapping(value="actualizar",produces="application/json",consumes="application/json")
	//produces el tipo de datos que envías, consumes el tipo de dato que recibes
	public Resultado actualizar(@RequestBody Resultado resultado) {
		return buscadorService.actualizarDescripcion(resultado.getUrl(), resultado.getDescripcion());
		//getDescripcion() devuelve el valor del parámetro nuevaDescripcion que está
		//en el método actualizarDescripción del BuscadorServiceImpl
	}
	
}

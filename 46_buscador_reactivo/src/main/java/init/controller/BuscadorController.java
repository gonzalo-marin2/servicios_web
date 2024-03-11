package init.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Resultado;
import init.service.interfaces.BuscadorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")//permitimos llamadas desde cualquier origen
@RestController
public class BuscadorController {
	@Autowired
	BuscadorService buscadorService;
	
	@GetMapping(value="buscar")//en los produces no se pone el json, normalmente
	public Flux<Resultado> buscar(@RequestParam("tematica") String tematica) {
		return buscadorService.buscar(tematica);
	}
	
	@PostMapping(value="alta")
	//consumes para decir que utiliza json
	public Mono<Void> alta(@RequestBody Resultado resultado) {
		//@requestbody es para que el cliente envíe un json
		return buscadorService.agregar(resultado);
	}
	
	@DeleteMapping(value="eliminar")
	public Flux<Resultado> eliminar(@RequestParam("url") String url){
		return buscadorService.eliminarResultado(url);
	}
	
	
	@PutMapping(value="actualizar",produces="application/json",consumes="application/json")
	//produces el tipo de datos que envías, consumes el tipo de dato que recibes
	public Mono<Resultado> actualizar(@RequestBody Resultado resultado) {
		return buscadorService.actualizarDescripcion(resultado.getUrl(), resultado.getDescripcion());
		//getDescripcion() devuelve el valor del parámetro nuevaDescripcion que está
		//en el método actualizarDescripción del BuscadorServiceImpl
	}
	
}

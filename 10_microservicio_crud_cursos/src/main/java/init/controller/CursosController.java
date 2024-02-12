package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.exceptions.CursoExistenteException;
import init.model.Curso;
import init.service.interfaces.CursosService;

@RestController
public class CursosController {
	@Autowired
	CursosService service;
	
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Curso>> cursos(){
		return new ResponseEntity<>(service.cursos(),HttpStatus.OK);
	}
	
	@GetMapping(value="curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Curso> buscar(@RequestParam("idCurso") int idCurso) {
		Curso curso=service.buscarCursoPorId(idCurso);
		if(curso!=null) {
			return new ResponseEntity<>(curso,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/*@GetMapping(value="cursosRango",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosRango(@RequestParam("min") double min, @RequestParam("max") double max){
		return service.buscarCursosRangoPrecios(min, max);
	}*/
	
	@GetMapping(value="cursos/{min}/{max}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosRango(@PathVariable("min") double min, @PathVariable("max") double max){
		return service.buscarCursosRangoPrecios(min, max);
	}
	
	@PostMapping(value="alta",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Curso>> alta(@RequestBody Curso curso){
		try {
			return new ResponseEntity<>(service.alta(curso),HttpStatus.OK);
		}
		catch(CursoExistenteException ex){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping(value="eliminar",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso eliminar(@RequestParam("denominacion") String denominacion) {
		return service.eliminarCurso(denominacion);
	}
	
	@PutMapping(value="actualizar")
	public void actualizar(@RequestParam("porcentaje") int porcentaje, @RequestParam("denominacion") String denominacion) {
		service.actualizarPrecio(porcentaje, denominacion);
	}
}

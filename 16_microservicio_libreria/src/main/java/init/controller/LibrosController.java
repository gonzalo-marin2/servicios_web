package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Libro;
import init.service.interfaces.LibrosService;

@RestController
public class LibrosController {

	@Autowired
	LibrosService librosService;
	
	//acceso libre
	@GetMapping(value="libros",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> catalogo(){
		return new ResponseEntity<>(librosService.libros(),HttpStatus.OK);
	}
	
	//acceso libre
	@GetMapping(value="tematicas",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> tematicas(){
		return new ResponseEntity<>(librosService.tematicas(),HttpStatus.OK);
	}
	
	//usuarios autenticados
	@GetMapping(value="isbn",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> buscarPorIsbn(@RequestParam("isbn") int isbn) {
		Libro lb=librosService.libroPorIsbn(isbn);
		if(lb!=null) {
			return new ResponseEntity<>(lb,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//administrador
	@PostMapping(value="alta",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Libro> alta(@RequestBody Libro libro){
		librosService.alta(libro);
		return librosService.libros();
	}
}

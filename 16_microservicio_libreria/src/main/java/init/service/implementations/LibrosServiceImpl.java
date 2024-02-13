package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.LibrosDao;
import init.exceptions.LibroExistenteException;
import init.model.Libro;
import init.service.interfaces.LibrosService;

@Service
public class LibrosServiceImpl implements LibrosService {

	@Autowired
	LibrosDao librosDao;
	
	@Override
	public List<Libro> libros() {
		return librosDao.findAll();
	}

	@Override
	public List<Libro> librosPorTematica(String tematica) {
		return librosDao.findByTematica(tematica);
	}

	@Override
	public Libro libroPorIsbn(int isbn) {
		return librosDao.findByIsbn(isbn);
	}

	@Override
	public List<Libro> alta(Libro libro) throws LibroExistenteException() {
		if(librosDao.findByIsbn(libro.getIsbn())!=null) {
			throw new LibroExistenteException();
		}
		librosDao.save(libro);
		return libros();
	}


}

package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.LibrosDao;
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
	public List<String> tematicas() {
		/*return librosDao.findAll().stream()
				.map(l->l.getTematica())
				.distinct()
				.toList();*/
		return librosDao.findTematicas();
	}

	@Override
	public Libro libroPorIsbn(int isbn) {
		return librosDao.findByIsbn(isbn);
	}
	
	
	@Override
	public List<Libro> alta(Libro libro) {
		if(librosDao.findByIsbn(libro.getIsbn())==null) {
			librosDao.save(libro);
			return libros();
		}
		return null;
	}

}

	

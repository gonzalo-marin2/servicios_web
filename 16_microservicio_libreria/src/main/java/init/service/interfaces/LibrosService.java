package init.service.interfaces;

import java.util.List;

import init.model.Libro;

public interface LibrosService {
	List<Libro> libros();
	List<Libro> librosPorTematica(String tematica);
	Libro libroPorIsbn(int isbn);
	List<Libro> alta(Libro libro);
}

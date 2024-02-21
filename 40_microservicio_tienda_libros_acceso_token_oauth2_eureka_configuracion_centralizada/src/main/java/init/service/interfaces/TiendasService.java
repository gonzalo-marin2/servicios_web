package init.service.interfaces;

import java.util.List;

import init.model.Libro;

public interface TiendasService {
	List<String> tematicas();
	List<Libro> librosPorTematica(String tematica);
}

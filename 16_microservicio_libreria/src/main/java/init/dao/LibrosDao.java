package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Libro;

public interface LibrosDao extends JpaRepository<Libro, Integer> {
	
	List<Libro> findByTematica(String tematica);
	Libro findByIsbn(int isbn);
	Libro findByTitulo(String titulo);
		
	//métodos heredados
	//catalogo => findAll()
	//alta => save
}

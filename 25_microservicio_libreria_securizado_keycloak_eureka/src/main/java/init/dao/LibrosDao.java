package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Libro;

public interface LibrosDao extends JpaRepository<Libro, Integer> {

	@Query("select distinct l.tematica from Libro l")
	List<String> findTematicas();
	Libro findByIsbn(int isbn);
	//lo podemos sustituir por findById() ya que isbn es el id
	Libro findByTitulo(String titulo);
		
	//métodos heredados
	//catalogo => findAll()
	//alta => save
}

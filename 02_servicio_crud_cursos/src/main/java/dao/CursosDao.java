package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import model.Curso;

public interface CursosDao extends JpaRepository<Curso,Integer> {
	
	List<Curso> findByPrecioBetween(double p1, double p2);
	
	Curso findByDenominacion(String denominacion);//lo ponemos por si luego nos hace falta
	
	@Transactional
	@Modifying
	void deleteByDenominacion(String denominacion);
	//los delete y los update son void, se modifica en la lógica de negocio
	
	@Transactional
	@Modifying
	@Query("update Curso c set c.precio=c.precio*100+?1/100 where c.nombre=?2")
	void updatePrecio(int porcentaje, String denominacion);
	
	//heredados
	//List<Curso> findAll();
	//Curso findByIdCurso(int idCurso);
	//List<Curso> save(Curso curso); este método tb actualiza un registro
}

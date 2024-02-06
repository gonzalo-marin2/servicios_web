package dao;

import java.util.List;

import model.Curso;

public interface CursosDao {
	List<Curso> findAll();
	Curso findByIdCurso(int idCurso);
	List<Curso> cursoMax(int minimo, int maximo);
}

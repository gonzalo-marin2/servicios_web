package service.interfaces;

import java.util.List;

import model.Curso;

public interface CursosService {
	List<Curso> mostrarCursos();
	Curso buscarCursoPorId(int idCurso);
	List<Curso> buscarPorRangoPrecio(int minimo, int maximo);
	List<Curso> alta(Curso curso);
	Curso eliminarCurso(String denominacion);
	Curso actualizarPrecio(String denominacion);
}

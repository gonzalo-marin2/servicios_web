package service.interfaces;

import java.util.List;

import model.Curso;

public interface CursosService {
	List<Curso> cursos();
	Curso buscarCursoPorId(int idCurso);
	List<Curso> buscarCursosRangoPrecios(double minimo, double maximo);
	List<Curso> alta(Curso curso);
	Curso eliminarCurso(String denominacion);
	void actualizarPrecio(int porcentaje, String denominacion);
}

package init.service.interfaces;

import java.util.List;

import init.exceptions.CursoExistenteException;
import init.model.Curso;

public interface CursosService {
	List<Curso> cursos();
	Curso buscarCursoPorId(int idCurso);
	List<Curso> buscarCursosRangoPrecios(double minimo, double maximo);
	List<Curso> alta(Curso curso) throws CursoExistenteException;
	Curso eliminarCurso(String denominacion);
	void actualizarPrecio(int porcentaje, String denominacion);
}

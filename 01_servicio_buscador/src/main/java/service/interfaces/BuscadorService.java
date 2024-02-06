package service.interfaces;

import java.util.List;

import model.Resultado;

public interface BuscadorService {
	List<Resultado> buscar(String tematica);
	void agregar(Resultado resultado);
	List<Resultado> eliminarResultado(String url);
	Resultado actualizarDescripcion(String url, String nuevaDescripcion);
}

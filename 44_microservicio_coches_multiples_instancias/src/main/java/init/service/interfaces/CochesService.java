package init.service.interfaces;

import java.util.List;

import init.model.Coche;

public interface CochesService {
	void alta(Coche coche);
	Coche eliminar(String matricula);
	void actualizarCoche(Coche coche);
	List<Coche> cochesPorMarca(String marca);
	List<Coche> cochesPorPrecioMax(double precioMax);
	List<Coche> catalogo();
	
}

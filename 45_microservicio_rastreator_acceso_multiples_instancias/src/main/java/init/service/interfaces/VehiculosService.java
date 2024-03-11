package init.service.interfaces;

import java.util.List;

import init.model.Vehiculo;

public interface VehiculosService {
	List<Vehiculo> buscarPorRangoKilometros(int minimo, int maximo);
	List<Vehiculo> buscarPorPrecioMax(double precioMax);
}

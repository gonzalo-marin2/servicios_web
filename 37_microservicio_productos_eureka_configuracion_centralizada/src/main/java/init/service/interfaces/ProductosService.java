package init.service.interfaces;

import java.util.List;

import init.model.Producto;

public interface ProductosService {
	List<Producto> productos();
	void actualizarStock(int codigoProducto, int unidades);
	double precioProducto(int codigoProducto);
}

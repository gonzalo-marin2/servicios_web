package init.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.ProductosDao;
import init.model.Producto;
import init.service.interfaces.ProductosService;

@Service
public class ProductosServiceImpl implements ProductosService {
	
	@Autowired
	ProductosDao productosDao;

	@Override
	public List<Producto> productos() {
		return productosDao.findAll();
	}

	@Override
	public void actualizarStock(int codigoProducto, int unidades) {
		productosDao.actualizarStock(codigoProducto, unidades);
		//forma de Antonio
		
		 /*Optional<Producto> prod=productosDao.findById(codigoProducto);
		 if(prod.isPresent()) {
			 Producto p=prod.get();
			 p.setStock(p.getStock()-unidades);
			 productosDao.save(p);
		 }	*/ 
	}

	@Override
	public double precioProducto(int codigoProducto) {
		/*Optional<Producto> prod=productosDao.findById(null);
		if(prod.isPresent()) {
			return prod.get().getPrecioUnitario();
		}else {
			return -1;
		}*/
		
		Producto producto=productoPorId(codigoProducto);
		if(producto!=null) {
			return producto.getPrecioUnitario();
		}else {
			return 0.0;
		}
	}
	
	public Producto productoPorId(int codigoProducto) {
		return productosDao.findById(codigoProducto).orElse(null);
	}

}

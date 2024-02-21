package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import init.model.Producto;
import jakarta.transaction.Transactional;

public interface ProductosDao extends JpaRepository<Producto, Integer> {
	//métodos que necesitamos:
	//1.- heredado: findAll()
	
	//2.- actualizar según el código y el número de unidades
	@Transactional
	@Modifying
	@Query("update Producto p set p.stock=p.stock-?2 where p.codigoProducto=?1")
	void actualizarStock(int id, int unidades);
	//teniendo el método save, no necesitaríamos un método para actualizar
	
	//3.- heredado findById()
	
	
}

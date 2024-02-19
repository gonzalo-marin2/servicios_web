package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Pedido;

public interface PedidosDao extends JpaRepository<Pedido, Integer> {
	//Necesitamos 2 métodos:
	
	//1.- alta=>método heredado save()
	
	//2.- pedidos=> método heredado findAll()
	
}

package init.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.dao.PedidosDao;
import init.model.Pedido;
import init.service.interfaces.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService {
	
	@Autowired
	RestClient restClient;
	@Autowired
	PedidosDao pedidosDao;
	
	String urlBase="http://servicio-productos/";
	
	@Override
	public List<Pedido> pedidos() {
		return pedidosDao.findAll();
	}
	
	@Override
	public void altaPedido(Pedido pedido) {
		pedido.setFechaPedido(new Date());//configuramos la fecha del pedido
		//obtenemos el precio del producto llamando al recurso remoto
		double precio=Double.parseDouble(restClient.get()
				.uri(urlBase+"precio/"+pedido.getCodigoProducto())
				.retrieve()
				.body(String.class));
		pedido.setTotal(pedido.getUnidades()*precio);//fijamos el precio total del pedido 
		//guardamos pedido
		pedidosDao.save(pedido);
		//actualizamos stock
		restClient.put()
		.uri(urlBase+"actualizarStock/"+pedido.getCodigoProducto()+"/"+pedido.getUnidades())
		.retrieve();
		
		
	}

	
	
	public Pedido buscarPorIdPedido(int idPedido) {
		return pedidosDao.findById(idPedido).orElse(null);
	}

}

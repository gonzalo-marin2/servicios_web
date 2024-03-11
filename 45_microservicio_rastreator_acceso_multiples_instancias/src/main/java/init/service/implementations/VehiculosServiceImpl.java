package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

//import init.model.Coche;
import init.model.Vehiculo;
import init.service.interfaces.VehiculosService;

@Service
public class VehiculosServiceImpl implements VehiculosService {
	
	@Autowired
	RestClient restClient;
	
	String url="http://servicio-coches/";

	@Override
	public List<Vehiculo> buscarPorRangoKilometros(int minimo, int maximo) {
		return Arrays.asList(restClient.get()
				.uri(url+"catalogo")
				.retrieve()
				.body(Vehiculo[].class))
				.stream()
				.peek(v->v.setTipo("coche"))//recorremos el array y le configuramos el tipo coche
				.filter(v->v.getKilometros()>=minimo && v.getKilometros()<=maximo)
				.toList();
	}

	@Override
	public List<Vehiculo> buscarPorPrecioMax(double precioMax) {
		return Arrays.asList(restClient.get()
				.uri(url+"buscarPrecioMax/"+precioMax)
				.retrieve()
				.body(Vehiculo[].class))
				.stream()
				.peek(v->v.setTipo("coche"))
				.toList();
	}

}

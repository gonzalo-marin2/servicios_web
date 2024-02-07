package service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Formacion;
import service.interfaces.FormacionService;

@Service
public class FormacionServiceImpl implements FormacionService {
	@Autowired
	RestClient restClient;
	
	String urlBase="http://localhost:8080/02_servicio_crud_cursos/";

	@Override
	public List<Formacion> catalogo() {
		return Arrays.asList(restClient.get()
				.uri(urlBase+"cursos")
				.retrieve()
				.body(Formacion[].class));//obtenemos un array)
	}

	@Override
	public List<Formacion> catalogoPorDuracionMax(int max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alta(Formacion formacion) {
		// TODO Auto-generated method stub

	}

}

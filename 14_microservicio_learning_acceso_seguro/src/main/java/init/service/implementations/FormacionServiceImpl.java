package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import init.model.Formacion;
import init.service.interfaces.FormacionService;

@Service
public class FormacionServiceImpl implements FormacionService {
	@Autowired
	RestClient restClient;
	
	//inyectamos las propiedades del application.properties
	@Value("${app.user}")
	String usuario;
	@Value("${app.password}")
	String pass;
	String urlBase="http://localhost:8500/";

	@Override
	public List<Formacion> catalogo() {
		return Arrays.asList(restClient.get()
				.uri(urlBase+"cursos")
				.retrieve()
				.body(Formacion[].class));//obtenemos un array)
	}

	@Override
	public List<Formacion> catalogoPorDuracionMax(int max) {
		return catalogo().stream()
				.filter(f->f.getHoras()<=max)
				.toList();
	}

	@Override
	public void alta(Formacion formacion) {
		try {
		restClient.post()
			.uri(urlBase+"alta")
			.contentType(MediaType.APPLICATION_JSON)
			.body(formacion)
			//enviamos las credenciales a través del encabezado
			.header("Authorization", "Basic "+getBase64(usuario,pass))
			.retrieve()
			.toBodilessEntity();//ResponseEntity<Void>
		}catch(HttpClientErrorException ex) {
			//tratamiento del error, que dependerá de lo que queramos hacer
			System.out.println("********Volcado del error********");
			ex.printStackTrace();
		}
	}
	
	private String getBase64(String us, String pwd) {
		String cad=us+":"+pwd;
		return Base64.getEncoder().encodeToString(cad.getBytes());
	}

}

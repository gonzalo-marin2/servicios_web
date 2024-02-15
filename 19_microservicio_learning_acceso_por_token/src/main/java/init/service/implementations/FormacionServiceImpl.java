package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.model.Formacion;
import init.model.TokenResponse;
import init.service.interfaces.FormacionService;

@Service
public class FormacionServiceImpl implements FormacionService {
	@Autowired
	RestClient restClient;
	
	//inyectamos las propiedades del application.properties
	@Value("${app.urlAuth}")
	String urlAuth;
	@Value("${app.username}")
	String username;
	@Value("${app.password}")
	String password;
	@Value("${app.client_id}")
	String clientId;
	@Value("${app.grant_type}")
	String grantType;
	
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
		restClient.post()
			.uri(urlBase+"alta")
			.contentType(MediaType.APPLICATION_JSON)
			.body(formacion)
			//enviamos las credenciales a través del encabezado
			.header("Authorization", "Bearer "+getToken())//getToken() se comunica con keycloak y obtiene el token 
			.retrieve()
			.toBodilessEntity();//ResponseEntity<Void>
	}
	
	private String getToken() {
		MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);
		params.add("grant_type", grantType);
		
		return restClient.post()
		.uri(urlAuth)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.body(params)
		.retrieve()
		.body(TokenResponse.class)//devuelve un objeto de la clase TokenResponse
		.getAccess_token();//accedemos a la propiedad que nos interesa
	}

}

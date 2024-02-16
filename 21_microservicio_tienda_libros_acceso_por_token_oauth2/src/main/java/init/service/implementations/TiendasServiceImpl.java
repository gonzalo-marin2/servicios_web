package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.model.Libro;
import init.model.TokenResponse;
import init.service.interfaces.TiendasService;
import jakarta.annotation.PostConstruct;

@Service
public class TiendasServiceImpl implements TiendasService {

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
	String urlBase="http://localhost:9100/";
	
	String token;
	@PostConstruct
	public void init() {//este método será llamado cuando la instancia esté disponible 
		token=getToken();
	}
	
	private List<Libro> libros(){
		return Arrays.asList(restClient.get()
				.uri(urlBase+"libros")
				.retrieve()
				.body(Libro[].class));//obtenemos un array)
	}
	
	@Override
	public List<String> tematicas() {
		return Arrays.asList(restClient.get()
				.uri(urlBase+"tematicas")
				.retrieve()
				.body(String[].class));//obtenemos un array)
		/*return libros().stream()
				.map(l->l.getTematica())
				.distinct()
				.toList();*/
	}

	@Override
	public List<Libro> librosPorTematica(String tematica) {
		return Arrays.asList(restClient.get()
				.uri(urlBase+"libros")
				.header("Authorization", "Bearer "+token)////enviamos las credenciales a través del encabezado
				.retrieve()
				.body(Libro[].class))
		.stream()
		.filter(l->l.getTematica().equals(tematica))
		.toList();
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

package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Libro;
import init.service.interfaces.TiendasService;

@Service
public class TiendasServiceImpl implements TiendasService {

	@Autowired
	RestClient restClient;
	
	//inyectamos las propiedades del application.properties
	@Value("${app.user}")
	String usuario;
	@Value("${app.password}")
	String pass;
	String urlBase="http://localhost:9100/";
	
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
				.header("Authorization", "Basic "+getBase64(usuario,pass))
				.retrieve()
				.body(Libro[].class))
		.stream()
		.filter(l->l.getTematica().equals(tematica))
		.toList();
	}
	
	private String getBase64(String us, String pwd) {
		String cad=us+":"+pwd;
		return Base64.getEncoder().encodeToString(cad.getBytes());
	}

}

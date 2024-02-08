package service.implementations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Pais;
import service.interfaces.PaisService;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	RestClient restClient;
	
	
	
	private List<Pais> paises() {
		String url="https://restcountries.com/v2/all";
		return Arrays.asList(restClient.get()
				.uri(url)
				.retrieve()
				.body(Pais[].class));
	}

	@Override
	public List<String> continentes() {
		return paises().stream()
				.map(p->p.getContinente())//transformamos la lista de Pais en lista de string
				.distinct()
				.toList();//convierte String final en lista
	}

	@Override
	public List<Pais> paisesPorContinente(String continente) {
		return paises().stream()
				.filter(p->p.getContinente().equals(continente))
				.toList();
	}

	@Override
	public Pais paisMasPoblado() {
		return paises().stream()
				.max(Comparator.comparingLong(p->p.getPoblacion()))
				.orElse(null);
	}

}

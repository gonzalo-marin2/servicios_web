package init.service.implementations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import init.model.Resultado;
import init.service.interfaces.BuscadorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BuscadorServiceImpl implements BuscadorService {
	
	//static para que no se pierdan los datos
	static List<Resultado> resultados=new ArrayList<>(List.of(
			new Resultado("http://www.fnac.es/","libros","Libros y más"),
			new Resultado("http://www.mybook.com/","libros","librería de temas varios"),
			new Resultado("http://www.game.es/","juegos","Juegos variados"),
			new Resultado("http://www.music.es/","música","Lamejor música"),
			new Resultado("http://www.tech.com/","libros","Libros técnicos"),
			new Resultado("http://www.eljuego.es/","juegos","Juegos on-line"))); 

	@Override
	public Flux<Resultado> buscar(String tematica) {
		return Flux.fromIterable(resultados)//Flux<Resultado>
				.filter(r->r.getTematica().toLowerCase().equals(tematica.toLowerCase()))//Flux<Resultado>
				.delayElements(Duration.ofSeconds(3));//hace una entrega de datos cada 3 segundos
		/*return resultados.stream()
				.filter(r->r.getTematica().toLowerCase().equals(tematica.toLowerCase()))
				.toList();*/
	}

	@Override
	public Mono<Void> agregar(Resultado resultado) {
		return null;
		//resultados.add(resultado);
	}

	@Override
	public Flux<Resultado> eliminarResultado(String url) {
		/*resultados.removeIf(r->r.getUrl().equals(url));
		return resultados;*/
		return null;
	}

	@Override
	public Mono<Resultado> actualizarDescripcion(String url, String nuevaDescripcion) {
		/*Resultado resultado=resultados.stream()
		.filter(r->r.getUrl().equals(url))
		.findFirst()
		.orElse(null);//devuelve null si no existe
		if(resultado!=null) {
			resultado.setDescripcion(nuevaDescripcion);
		}
		return resultado;*/
		return null;
	}
	
	
	
	

}

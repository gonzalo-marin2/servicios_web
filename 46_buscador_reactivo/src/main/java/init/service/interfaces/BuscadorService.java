package init.service.interfaces;

import java.util.List;

import init.model.Resultado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BuscadorService {
	Flux<Resultado> buscar(String tematica);
	Mono<Void> agregar(Resultado resultado);
	Flux<Resultado> eliminarResultado(String url);
	Mono<Resultado> actualizarDescripcion(String url, String nuevaDescripcion);
}

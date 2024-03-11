package init.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import init.model.Resultado;
@Component
public class TestBuscador implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		//la tarea es conectarse con el anterior y suscribirse al flujo de datos
		//queremos que nos imprima las url´s
		
		String url="http://localhost:8080/buscar";
		WebClient client=WebClient.create();//creamos el objeto WebClient
		client.get()
		.uri(url+"?tematica=libros")
		.retrieve()
		.bodyToFlux(Resultado.class)//Flux<Resultado>
		.doOnComplete(()->System.out.println("Se acabó!!"))//cuando termina de traer datos, muestra el mensaje después de la suscripción
		//nos suscribimos al flujo
		.subscribe(r->System.out.println(r.getUrl()));//devuelve un Disponsable

	}

}

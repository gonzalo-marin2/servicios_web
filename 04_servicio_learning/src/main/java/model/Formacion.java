package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Formacion {
	@JsonProperty(value = "denominacion")//trabaja en ambos sentidos
	/*//usamos la anotación para darle a nombre el valor denominacion que es el que está
	//en cursos. Sólo serialización
	@JsonAlias(value = "denominacion")*/
	private String nombre;
	@JsonProperty(value = "duracion")
	//@JsonAlias(value = "duracion")
	private int horas;
	private double precio;
}

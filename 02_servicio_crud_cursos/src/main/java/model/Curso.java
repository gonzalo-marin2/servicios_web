package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cursos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Curso {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCurso;
	private String denominacion;
	private int duracion;
	private double precio;
	
}

package service.interfaces;

import java.util.List;

import model.Formacion;

public interface FormacionService {
	List<Formacion> catalogo();
	List<Formacion> catalogoPorDuracionMax(int max);
	void alta(Formacion formacion);
}

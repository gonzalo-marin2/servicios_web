package service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CursosDao;
import model.Curso;
import service.interfaces.CursosService;

@Service
public class CursosServiceImpl implements CursosService {
	@Autowired
	CursosDao cursosDao;

	@Override
	public List<Curso> cursos() {
		return cursosDao.findAll();
	}

	@Override
	public Curso buscarCursoPorId(int idCurso) {
		return cursosDao.findById(idCurso).orElse(null);
	}

	@Override
	public List<Curso> buscarCursosRangoPrecios(double minimo, double maximo) {
		return cursosDao.findByPrecioBetween(minimo, maximo);
	}

	@Override
	public List<Curso> alta(Curso curso) {
		cursosDao.save(curso);
		return cursos();
	}

	@Override
	public Curso eliminarCurso(String denominacion) {
		Curso curso=cursosDao.findByDenominacion(denominacion);
		if(curso!=null) {
			cursosDao.deleteByDenominacion(denominacion);
		}
		return curso;
	}

	@Override
	public void actualizarPrecio(int porcentaje, String denominacion) {
		cursosDao.updatePrecio(porcentaje, denominacion);	
	}

}

package Filtro;

import java.time.LocalDate;
import java.util.List;

import Muestra.Muestra;

public abstract class FiltroPorFecha extends Filtro {

	private LocalDate fecha;

	@Override
	public abstract List<Muestra> buscar(List<Muestra> muestras);


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	

}

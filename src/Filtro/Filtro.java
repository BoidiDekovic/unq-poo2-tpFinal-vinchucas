package Filtro;

import java.util.List;
import Muestra.Muestra;

public abstract class Filtro {

	public abstract List<Muestra> buscar(List<Muestra> muestras);
	
	public void agregarFiltro(Filtro filtro) {
		throw new UnsupportedOperationException();
	}
	
	public void quitarFiltro(Filtro filtro) {
		throw new UnsupportedOperationException();
	}

	public Filtro getFiltroHijo(int index) {
		throw new UnsupportedOperationException();
	}
	
}

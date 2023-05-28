package Filtro;

import java.util.List;

import Muestra.Muestra;
import Sistema.Sistema;

public abstract class Filtro {

	protected Sistema sistema;
	
	public abstract List<Muestra> buscar();
	
	public void agregarFiltro(Filtro filtro) {
		throw new UnsupportedOperationException();
	}
	
	public void quitarFiltro(Filtro filtro) {
		throw new UnsupportedOperationException();
	}

	public Filtro getFiltroHijo(int index) {
		throw new UnsupportedOperationException();
	}
	
	public Sistema getSistema() {
		return sistema;
	}
	
}

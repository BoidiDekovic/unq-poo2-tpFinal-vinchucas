package Filtro;


import java.util.List;

import Muestra.Muestra;

public abstract class OperadorDeFiltros extends Filtro {

	private List<Filtro> filtros ;
	@Override
	public abstract List<Muestra> buscar(List<Muestra> muestras);
	
	@Override
	public void agregarFiltro(Filtro filtro) {
		this.filtros.add(filtro);
	}
	@Override
	public void quitarFiltro(Filtro filtro) {
		this.filtros.remove(filtro);
	}
	@Override
	public Filtro getFiltroHijo(int index) {
		return this.getFiltroHijo(index);
	}


}
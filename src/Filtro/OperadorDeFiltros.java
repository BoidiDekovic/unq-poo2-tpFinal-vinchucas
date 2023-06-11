package Filtro;


import java.util.List;

import Muestra.Muestra;

public abstract class OperadorDeFiltros extends Filtro {
	
	// PRECONDICION: solo se puede agregar dos filtros
	protected List<Filtro> filtros;
	
	// PRECONDICION: se necesitan dos filtros
	@Override
	public abstract List<Muestra> buscar(List<Muestra> muestras);
	
	@Override
	public void agregarFiltro(Filtro filtro) {
		this.validarNoMasDeDosFiltros();
		this.filtros.add(filtro);
	}
	
	private void validarNoMasDeDosFiltros() {
		if(this.filtros.size() == 2) {
			throw new UnsupportedOperationException
				("No se pueden agregar mas de dos filtros");
		}
	}
	
	@Override
	public void quitarFiltro(Filtro filtro) {
		this.filtros.remove(filtro);
	}
	@Override
	public Filtro getFiltroHijo(int index) {
		return this.filtros.get(index);
	}

	public List<Filtro> getFiltrosHijos(){
		return this.filtros;
	}
	
}

package Filtro;


import java.util.Arrays;
import java.util.List;

import Muestra.Muestra;

public abstract class OperadorDeFiltros extends Filtro {
	
	protected List<Filtro> filtros;
	
		
	public OperadorDeFiltros(Filtro filtro1, Filtro filtro2) {
		super();
		this.filtros = Arrays.asList(filtro1, filtro2);
	}

	@Override
	public abstract List<Muestra> buscar(List<Muestra> muestras);

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
	
	public void quitarFiltro(Filtro filtro) {
		this.filtros.remove(filtro);
	}
	
	public Filtro getFiltroHijo(int index) {
		return this.filtros.get(index);
	}

	public List<Filtro> getFiltrosHijos(){
		return this.filtros;
	}
	
}

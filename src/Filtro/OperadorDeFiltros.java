package Filtro;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Muestra.Muestra;

public abstract class OperadorDeFiltros implements Filtro {
	
	protected List<Filtro> filtros;
	
		
	public OperadorDeFiltros() {
		super();
		this.filtros = new ArrayList<Filtro>();
	}

	@Override
	public abstract List<Muestra> buscar(List<Muestra> muestras);

	public void agregarFiltro(Filtro filtro) {
		this.filtros.add(filtro);
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

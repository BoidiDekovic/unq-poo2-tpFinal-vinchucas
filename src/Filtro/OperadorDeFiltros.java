package Filtro;

import java.util.List;

import Muestra.Muestra;

public abstract class OperadorDeFiltros implements Filtro {
	
	protected Filtro filtro1;
	protected Filtro filtro2;
	
		
	public OperadorDeFiltros(Filtro filtro1, Filtro filtro2) {
		super();
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}

	@Override
	public abstract List<Muestra> buscar(List<Muestra> muestras);

	public Filtro getFiltro1() {
		return filtro1;
	}

	public void setFiltro1(Filtro filtro1) {
		this.filtro1 = filtro1;
	}
	
	public Filtro getFiltro2() {
		return filtro2;
	}

	public void setFiltro2(Filtro filtro2) {
		this.filtro2 = filtro2;
	}

	
}

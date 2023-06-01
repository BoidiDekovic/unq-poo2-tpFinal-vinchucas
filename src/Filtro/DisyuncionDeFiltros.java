package Filtro;


import java.util.ArrayList;
import java.util.List;

import Muestra.Muestra;


public class DisyuncionDeFiltros extends OperadorDeFiltros{

	private Filtro[] filtros;
	
	
	
	
	public DisyuncionDeFiltros() {
		super();
		this.filtros = new Filtro[2];
	}


	@Override
	public List<Muestra> buscar(List<Muestra> muestras) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

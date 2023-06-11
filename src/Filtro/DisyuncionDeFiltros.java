package Filtro;


import java.util.ArrayList;
import java.util.List;

import Muestra.Muestra;


public class DisyuncionDeFiltros extends OperadorDeFiltros{
	
	public DisyuncionDeFiltros() {
		super();
		this.filtros = new ArrayList<Filtro>();
	}


	@Override
	public List<Muestra> buscar(List<Muestra> muestras) {
		List<Muestra> muestrasDeFiltro1 = filtros.get(0).buscar(muestras);
		List<Muestra> muestrasDeFiltro2 = filtros.get(1).buscar(muestras);
		muestrasDeFiltro1.addAll(muestrasDeFiltro2);
		return muestrasDeFiltro1;
	}
	
}

package Filtro;

import java.util.ArrayList;
import java.util.List;

import Muestra.Muestra;

public class ConjuncionDeFiltros extends OperadorDeFiltros {


	public ConjuncionDeFiltros(Filtro filtro1, Filtro filtro2) {
		super(filtro1, filtro2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Muestra> buscar(List<Muestra> muestras) {
		List<Muestra> muestrasDeFiltro1 = this.filtro1.buscar(muestras);
		List<Muestra> muestrasDeFiltro2 = this.filtro2.buscar(muestras);
		muestrasDeFiltro1.retainAll(muestrasDeFiltro2);
		return muestrasDeFiltro1;
	}
	
}

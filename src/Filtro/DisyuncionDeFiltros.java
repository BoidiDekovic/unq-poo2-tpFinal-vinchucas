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
	public List<Muestra> operar(List<Muestra> muestras) {
		List<Muestra> muestrasDeFiltro1 = filtros.get(0).buscar(muestras);
		List<Muestra> muestrasDeFiltro2 = filtros.get(1).buscar(muestras);
		for(Muestra muestra : muestrasDeFiltro2) {
			if(!muestrasDeFiltro1.contains(muestra)) {
				muestrasDeFiltro1.add(muestra);
			}
		}
		return muestrasDeFiltro1;
	}
	
}

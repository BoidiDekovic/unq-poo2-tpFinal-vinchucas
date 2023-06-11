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
		this.validarHayDosFiltros();
		List<Muestra> muestrasDeFiltro1 = filtros.get(0).buscar(muestras);
		List<Muestra> muestrasDeFiltro2 = filtros.get(1).buscar(muestras);
		for(Muestra muestra : muestrasDeFiltro2) {
			if(!muestrasDeFiltro1.contains(muestra)) {
				muestrasDeFiltro1.add(muestra);
			}
		}
		return muestrasDeFiltro1;
	}
	
	private void validarHayDosFiltros() {
		if(this.filtros.size() != 2) {
			throw new UnsupportedOperationException
			("No se puede operar si no hay dos filtros");
		}
	}
	
}

package Filtro;


import java.util.List;

import Muestra.Muestra;


public class DisyuncionDeFiltros extends OperadorDeFiltros{
	
	public DisyuncionDeFiltros(Filtro filtro1, Filtro filtro2) {
		super(filtro1, filtro2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Muestra> buscar(List<Muestra> muestras) {
		List<Muestra> muestrasDeFiltro1 = this.filtro1.buscar(muestras);
		List<Muestra> muestrasDeFiltro2 = this.filtro2.buscar(muestras);
		for(Muestra muestra : muestrasDeFiltro2) {
			if(!muestrasDeFiltro1.contains(muestra)) {
				muestrasDeFiltro1.add(muestra);
			}
		}
		return muestrasDeFiltro1;
	}
	
}

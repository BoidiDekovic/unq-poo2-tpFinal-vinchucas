package Organizacion;

import Muestra.Muestra;
import ZonaDeCobertura.ZonaDeCobertura;

public abstract class EstrategiaOrganizacion {
	
	private FuncionalidadExterna funcionalidad;
	
	public abstract void actualizar(ZonaDeCobertura zo, Muestra m);
	
	public void setFuncionalidad(FuncionalidadExterna fe) {
		this.funcionalidad = fe;
	}

}

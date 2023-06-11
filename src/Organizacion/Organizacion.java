package Organizacion;

import Muestra.Muestra;
import ZonaDeCobertura.ObservadorZona;
import ZonaDeCobertura.Ubicacion;
import ZonaDeCobertura.ZonaDeCobertura;

public class Organizacion implements ObservadorZona {
	
	private FuncionalidadExterna funcionalidadNuevaMuestra;
	private FuncionalidadExterna funcionalidadMuestraVerificada;
	private int cantidadDeTrabajadores;
	private TipoOrganizacion tipo;
	private Ubicacion ubicacion;
	
	public Organizacion(int cantidadDeTrabajadores, TipoOrganizacion tipo, Ubicacion ubicacion) {
		super();
		this.cantidadDeTrabajadores = cantidadDeTrabajadores;
		this.tipo = tipo;
		this.ubicacion = ubicacion;
	}

	@Override
	public void actualizarMuestraVerificada(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		if (this.funcionalidadMuestraVerificada != null) {
			this.funcionalidadMuestraVerificada.nuevoEvento(this, zonaDeCobertura, muestra);
		}
	}

	@Override
	public void actualizarNuevaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		if (this.funcionalidadNuevaMuestra != null) {
			this.funcionalidadNuevaMuestra.nuevoEvento(this, zonaDeCobertura, muestra);
		}
	}
	public FuncionalidadExterna getFuncionalidadNuevaMuestra() {
		return funcionalidadNuevaMuestra;
	}

	public void setFuncionalidadNuevaMuestra(FuncionalidadExterna funcionalidadNuevaMuestra) {
		this.funcionalidadNuevaMuestra = funcionalidadNuevaMuestra;
	}

	public FuncionalidadExterna getFuncionalidadMuestraVerificada() {
		return funcionalidadMuestraVerificada;
	}

	public void setFuncionalidadMuestraVerificada(FuncionalidadExterna funcionalidadMuestraVerificada) {
		this.funcionalidadMuestraVerificada = funcionalidadMuestraVerificada;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;	
	}

}

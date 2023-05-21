package Organizacion;

import Muestra.Muestra;
import ZonaDeCobertura.Ubicacion;
import ZonaDeCobertura.ZonaDeCobertura;

public class Organizacion {
	
	private int cantidadDeTrabajadores;
	private TipoOrganizacion tipo;
	private EstrategiaOrganizacion estrategia;
	private Ubicacion ubicacion;
	
	public Organizacion(int cantidadDeTrabajadores, TipoOrganizacion tipo, EstrategiaOrganizacion estrategia,
			Ubicacion ubicacion) {
		super();
		this.cantidadDeTrabajadores = cantidadDeTrabajadores;
		this.tipo = tipo;
		this.estrategia = estrategia;
		this.ubicacion = ubicacion;
	}
	
	public void actualizar(ZonaDeCobertura zo, Muestra m) {
		
	}
	
	public void setEstrategia(EstrategiaOrganizacion eo) {
		this.estrategia = eo;
	}

}

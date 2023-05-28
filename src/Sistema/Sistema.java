package Sistema;

import java.util.ArrayList;
import java.util.List;

import Muestra.Muestra;
import ZonaDeCobertura.Distancia;
import ZonaDeCobertura.ZonaDeCobertura;

public class Sistema {
	
	private List<Muestra>  muestras; 
	private List<ZonaDeCobertura> zonasDeCobertura;
	
	public Sistema() {
		super();
		this.muestras = new ArrayList<Muestra>();
		this.zonasDeCobertura = new ArrayList<ZonaDeCobertura>();
	}
	
	public void notificarVerifiacionMuestra(Muestra muestra) {
		this.muestras.add(muestra);
		 /// completar 
	}


	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
		
	}
	public List<Muestra> muestrasAMenosDistanciaDe(Distancia distancia, Muestra muestra){
		return null;
	}
	
	
}

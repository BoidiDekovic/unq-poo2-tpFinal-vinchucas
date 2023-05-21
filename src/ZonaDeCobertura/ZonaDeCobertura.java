package ZonaDeCobertura;

import java.util.ArrayList;
import java.util.List;

import Muestra.Muestra;

public class ZonaDeCobertura {
	
	
	private String nombre;
	private Ubicacion epicentro ;
	private List<Muestra> muestras;
	private List<Observadores> observadores;
	
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro) {
		super();
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.muestras = new ArrayList<Muestra>();
		this.observadores = new ArrayList <Observadores>();
	}
	
	
	
	
	public void notificar(String s , Ubicacion u , Muestra m) {
		
	}
	
	public void agregarObservador(Observador ob) {
	
	}
	
	public void sacarObservador(Observador ob) {
		
	}
	
	public void agregarMuestra(Muestra muestra) {
		
	}
	
	public void  notificaVerificacionMuestra(Muestra muestra) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}

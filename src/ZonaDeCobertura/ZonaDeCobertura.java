package ZonaDeCobertura;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import Muestra.Muestra;

public class ZonaDeCobertura {
	
	
	private String nombre;
	private Ubicacion epicentro ;
	private List<Muestra> muestras;
	private List<Observer> observadores;
	
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro) {
		super();
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.muestras = new ArrayList<Muestra>();
		this.observadores = new ArrayList <Observer>();
	}
	
	public void notificar(String s , Ubicacion u , Muestra m) {
		
	}
	
	public void agregarObservador(Observer ob) {
	
	}
	
	public void sacarObservador(Observer ob) {
		
	}
	
	public void agregarMuestra(Muestra muestra) {
		
	}
	
	public void  notificaVerificacionMuestra(Muestra muestra) {
		
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

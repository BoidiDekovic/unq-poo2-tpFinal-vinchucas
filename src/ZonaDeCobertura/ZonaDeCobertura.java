package ZonaDeCobertura;

import java.util.ArrayList;
import java.util.List;
import Muestra.Muestra;
import Sistema.Sistema;

public class ZonaDeCobertura {
	
	
	private String nombre;
	private Ubicacion epicentro ;
	private List<Muestra> muestras;
	private List<ObservadorZona> observadores;
	
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro) {
		super();
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.muestras = new ArrayList<Muestra>();
		this.observadores = new ArrayList <ObservadorZona>();
	}
	
	public void notificarNuevaMuestra(Muestra muestra) {
		this.observadores.stream().forEach(o -> o.actualizarNuevaMuestra(this, muestra));
	}
	
	public void notificarVerificacionMuestra(Muestra muestra) {
		this.observadores.stream().forEach(o -> o.actualizarMuestraVerificada(this, muestra));
	}
	
	public void agregarObservador(ObservadorZona observadorZona) {
		this.observadores.add(observadorZona);
	}
	
	public void sacarObservador(ObservadorZona observadorZona) {
		this.observadores.remove(observadorZona);
	}
	
	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	public List<ObservadorZona> getObservadores() {
		return observadores;
	}
	
	public List<ZonaDeCobertura> zonasSolapadas(Sistema sistema) {
		return sistema.zonasSolapadasDe(this);
	}
	
}

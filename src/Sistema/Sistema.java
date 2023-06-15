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
	
	public void notificarVerificacionMuestraAZonas(Muestra muestra) {
		this.zonasDeCobertura.stream().
					filter(z -> z.getMuestras().contains(muestra)).
					forEach(z -> z.notificarVerificacionMuestra(muestra));
	}

	public List<Muestra> muestrasAMenosDe(Distancia distancia, Muestra muestra){
		return this.muestras.stream()
			.filter(m -> muestra.getUbicacion().esUbicacionAMenosDe(m.getUbicacion(), distancia)).toList();
	}

	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
		this.zonasDeCobertura.stream()
			.filter(z -> z.tieneALaUbicacionDentroDelRadio(muestra.getUbicacion()))
			.forEach(z -> z.agregarMuestra(muestra));;
	}
	
	public void agregarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		this.zonasDeCobertura.add(zonaDeCobertura);
	}
	
	public List<Muestra> getMuestras() {
		return muestras;
	}

	public List<ZonaDeCobertura> getZonasDeCobertura() {
		return this.zonasDeCobertura;
	}

	public List<ZonaDeCobertura> zonasSolapadasDe(ZonaDeCobertura zonaDeCobertura) {
		return this.zonasDeCobertura.stream()
				.filter(z -> z.esZonaSolapada(zonaDeCobertura))
				.toList();
	}
}

package Sistema;

import java.util.ArrayList;
import java.util.List;

import Muestra.Muestra;
import ZonaDeCobertura.Distancia;
import ZonaDeCobertura.ZonaDeCobertura;

public class Sistema {
	
	private List<Muestra>  muestras; 
	private List<ZonaDeCobertura> zonasDeCoberturas;
	
	public Sistema() {
		super();
		this.muestras = new ArrayList<Muestra>();
		this.zonasDeCoberturas = new ArrayList<ZonaDeCobertura>();
	}
	
	public void notificarVerificacionMuestraAZonas(Muestra muestra) {
		this.zonasDeCoberturas.stream().
					filter(z -> z.getMuestras().contains(muestra)).
					forEach(z -> z.notificarVerificacionMuestra(muestra));
	}

	public List<Muestra> muestrasAMenosDe(Distancia distancia, Muestra muestra){
		return this.muestras.stream()
			.filter(m -> muestra.getUbicacion().esUbicacionAMenosDe(m.getUbicacion(), distancia)).toList();
	}

	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
		this.zonasDeCoberturas.stream()
			.filter(z -> z.tieneALaUbicacionDentroDelRadio(muestra.getUbicacion()))
			.forEach(z -> z.agregarMuestra(muestra));;
	}
	
	public void agregarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		this.zonasDeCoberturas.add(zonaDeCobertura);
	}
	
	public List<Muestra> getMuestras() {
		return muestras;
	}

	public List<ZonaDeCobertura> getZonasDeCoberturas() {
		return this.zonasDeCoberturas;
	}

	public List<ZonaDeCobertura> zonasSolapadasDe(ZonaDeCobertura zonaDeCobertura) {
		return this.zonasDeCoberturas.stream()
				.filter(z -> z.esZonaSolapada(zonaDeCobertura))
				.toList();
	}
}

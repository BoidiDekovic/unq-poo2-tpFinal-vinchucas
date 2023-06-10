package Filtro;

import java.util.List;

import Muestra.EstadoMuestra;
import Muestra.Muestra;

public class FiltroPorNivelDeVerificacion extends Filtro {

	private EstadoMuestra nivelVerificacion;
	
	
	/**
	 * @param nivelVerificacion
	 */
	public FiltroPorNivelDeVerificacion(EstadoMuestra nivelVerificacion) {
		super();
		this.nivelVerificacion = nivelVerificacion;
	}


	@Override
	public List<Muestra> buscar(List<Muestra> muestras) {
		return muestras.stream()
				 .filter(m -> m.getEstadoMuestra().equals(this.getNivelVerificacion()))
				 .toList();
	}


	public EstadoMuestra getNivelVerificacion() {
		return nivelVerificacion;
	}


	public void setNivelVerificacion(EstadoMuestra nivelVerificacion) {
		this.nivelVerificacion = nivelVerificacion;
	}

	
}

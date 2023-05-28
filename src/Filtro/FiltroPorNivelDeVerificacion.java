package Filtro;

import java.util.List;

import Muestra.EstadoMuestra;
import Muestra.Muestra;
import Sistema.Sistema;

public class FiltroPorNivelDeVerificacion extends Filtro {

	private EstadoMuestra nivelVerificacion;
	
	
	/**
	 * @param nivelVerificacion
	 */
	public FiltroPorNivelDeVerificacion(EstadoMuestra nivelVerificacion, Sistema sistema) {
		super();
		this.nivelVerificacion = nivelVerificacion;
		this.sistema = sistema;
	}


	@Override
	public List<Muestra> buscar() {
		return this.sistema.getMuestras().stream()
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

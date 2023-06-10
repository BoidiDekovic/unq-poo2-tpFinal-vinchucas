package Filtro;

import java.time.LocalDate;
import java.util.List;

import Muestra.Muestra;

public class FiltroPorFechaDeUltimaVotacion extends FiltroPorFecha {

	/**
	 * @param nivelVerificacion
	 */
	public FiltroPorFechaDeUltimaVotacion(LocalDate fechaDeUltimaVotacion) {
		super();
		this.setFecha(fechaDeUltimaVotacion);
	}
	
	@Override
	public List<Muestra> buscar(List<Muestra> muestras) {
		
		return muestras.stream().filter(m -> m.getFechaUltimaVotacion().equals(this.getFecha()))
								.toList();
	}
	
	
}

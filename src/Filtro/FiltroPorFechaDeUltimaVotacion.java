package Filtro;

import java.time.LocalDate;
import java.util.List;

import Muestra.Muestra;
import Sistema.Sistema;

public class FiltroPorFechaDeUltimaVotacion extends FiltroPorFecha {

	/**
	 * @param nivelVerificacion
	 */
	public FiltroPorFechaDeUltimaVotacion(LocalDate fechaDeUltimaVotacion, Sistema sistema) {
		super();
		this.setFecha(fechaDeUltimaVotacion);
		this.sistema = sistema;
	}
	
	@Override
	public List<Muestra> buscar() {
		
		return this.getSistema().getMuestras().stream()
								.filter(m -> m.getFechaUltimaVotacion().equals(this.getFecha()))
								.toList();
	}
	
	
}

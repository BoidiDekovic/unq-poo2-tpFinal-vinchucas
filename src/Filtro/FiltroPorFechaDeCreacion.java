package Filtro;

import java.time.LocalDate;
import java.util.List;

import Muestra.Muestra;

public class FiltroPorFechaDeCreacion extends FiltroPorFecha {

	/**
	 * @param nivelVerificacion
	 */
	public FiltroPorFechaDeCreacion(LocalDate fechaDeCreacionDeMuestra) {
		super(fechaDeCreacionDeMuestra);
	}
	
	@Override
	public List<Muestra> buscar(List<Muestra> muestras) {
		return muestras.stream()
				.filter(m -> m.getFechaCreacion().equals(this.getFecha()))
				.toList();
	}

}

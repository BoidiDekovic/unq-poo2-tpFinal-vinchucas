package Filtro;

import java.time.LocalDate;
import java.util.List;

import Muestra.Muestra;
import Sistema.Sistema;

public class FiltroPorFechaDeCreacion extends FiltroPorFecha {

	/**
	 * @param nivelVerificacion
	 */
	public FiltroPorFechaDeCreacion(LocalDate fechaDeCreacionDeMuestra, Sistema sistema) {
		super();
		this.setFecha(fechaDeCreacionDeMuestra);
		this.sistema = sistema;
	}
	
	@Override
	public List<Muestra> buscar() {
		return this.getSistema().getMuestras().stream()
				.filter(m -> m.getFechaCreacion().equals(this.getFecha()))
				.toList();
	}

}

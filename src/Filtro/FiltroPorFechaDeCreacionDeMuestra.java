package Filtro;

import java.time.LocalDate;
import java.util.List;

import Muestra.Muestra;
import Sistema.Sistema;

public class FiltroPorFechaDeCreacionDeMuestra extends FiltroPorFecha {

	/**
	 * @param nivelVerificacion
	 */
	public FiltroPorFechaDeCreacionDeMuestra(LocalDate fechaDeCreacionDeMuestra, Sistema sistema) {
		super();
		this.setFecha(fechaDeCreacionDeMuestra);
		this.setSistema(sistema);
	}
	
	@Override
	public List<Muestra> buscar() {
		// TODO Auto-generated method stub
		return null;
	}

}

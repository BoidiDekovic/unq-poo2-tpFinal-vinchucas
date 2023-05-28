package Filtro;

import java.util.List;

import Muestra.Muestra;
import Muestra.TipoOpinion;
import Sistema.Sistema;

public class FiltroPorTipoInsecto extends Filtro {

	private TipoOpinion tipoInsecto;
	
	
	
	/**
	 * @param tipoInsecto
	 */
	public FiltroPorTipoInsecto(TipoOpinion tipoInsecto, Sistema sistema) {
		super();
		this.tipoInsecto = tipoInsecto;
		this.sistema = sistema;
	}



	@Override
	public List<Muestra> buscar() {
		return this.sistema.getMuestras().stream()
										 .filter(m -> m.resultadoActual().equals(this.tipoInsecto))
										 .toList();
	}


	public TipoOpinion getTipoInsecto() {
		return tipoInsecto;
	}

	public void setTipoInsecto(TipoOpinion tipoInsecto) {
		this.tipoInsecto = tipoInsecto;
	}

	
}

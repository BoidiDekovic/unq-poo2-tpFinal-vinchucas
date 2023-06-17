package Filtro;

import java.util.List;

import Muestra.Muestra;
import Muestra.TipoOpinion;

public class FiltroPorTipoInsecto implements Filtro {

	private TipoOpinion tipoInsecto;
	
	
	
	/**
	 * @param tipoInsecto
	 */
	public FiltroPorTipoInsecto(TipoOpinion tipoInsecto) {
		super();
		this.tipoInsecto = tipoInsecto;
	}



	@Override
	public List<Muestra> buscar(List<Muestra> muestras) {
		return muestras.stream().filter(m -> m.resultadoActual().equals(this.tipoInsecto))
								.toList();
	}


	public TipoOpinion getTipoInsecto() {
		return tipoInsecto;
	}

	public void setTipoInsecto(TipoOpinion tipoInsecto) {
		this.tipoInsecto = tipoInsecto;
	}

	
}

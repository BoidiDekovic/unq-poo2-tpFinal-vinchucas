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
		this.setSistema(sistema);
	}



	@Override
	public List<Muestra> buscar() {
		// TODO Auto-generated method stub
		return null;
	}


	public TipoOpinion getTipoInsecto() {
		return tipoInsecto;
	}

	public void setTipoInsecto(TipoOpinion tipoInsecto) {
		this.tipoInsecto = tipoInsecto;
	}

	
}

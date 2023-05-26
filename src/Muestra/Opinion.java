package Muestra;

import Usuario.EstadoUsuario;

public class Opinion {

	private TipoOpinion tipoOpinion ;
	private EstadoUsuario estadoAutor;
	
	
	public Opinion(TipoOpinion tipoOpinion, EstadoUsuario estadoAutor) {
		super();
		this.tipoOpinion = tipoOpinion;
		this.estadoAutor = estadoAutor;
	}


	public TipoOpinion getTipoOpinion() {
		return tipoOpinion;
	}
	
	public EstadoUsuario getEstadoAutor() {
		return estadoAutor;
	}
	
	
	
	
	
}
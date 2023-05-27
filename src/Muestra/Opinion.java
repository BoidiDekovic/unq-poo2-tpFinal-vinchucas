package Muestra;

import java.time.LocalDate;

import Usuario.EstadoUsuario;

public class Opinion {

	private TipoOpinion tipoOpinion ;
	private EstadoUsuario estadoAutor;
	private LocalDate fechaDeEnvio;
	
	
	public Opinion(TipoOpinion tipoOpinion, EstadoUsuario estadoAutor, LocalDate fechaDeEnvio) {
		super();
		this.tipoOpinion = tipoOpinion;
		this.estadoAutor = estadoAutor;
		this.fechaDeEnvio = fechaDeEnvio;
	}


	public TipoOpinion getTipoOpinion() {
		return tipoOpinion;
	}
	
	public EstadoUsuario getEstadoAutor() {
		return estadoAutor;
	}

	public LocalDate getFechaDeEnvio() {
		return fechaDeEnvio;
	}
	
	
	
	
	
}
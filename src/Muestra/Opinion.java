package Muestra;

import java.time.LocalDate;

import Usuario.EstadoUsuario;
import Usuario.Usuario;

public class Opinion {

	private TipoOpinion tipoOpinion ;
	private EstadoUsuario estadoAutorAlOpinar;
	private Usuario usuarioAutor;
	private LocalDate fechaDeEnvio;
	
	
	public Opinion(TipoOpinion tipoOpinion, Usuario usuarioAutor, LocalDate fechaDeEnvio) {
		super();
		this.tipoOpinion = tipoOpinion;
		this.estadoAutorAlOpinar = usuarioAutor.getEstado();
		this.usuarioAutor = usuarioAutor;
		this.fechaDeEnvio = fechaDeEnvio;
	}


	public TipoOpinion getTipoOpinion() {
		return tipoOpinion;
	}
	
	public LocalDate getFechaDeEnvio() {
		return fechaDeEnvio;
	}
	
	public boolean esOpinionDeExperto() {
		return this.estadoAutorAlOpinar.esExperto();
	}

	public Usuario getUsuarioAutor() {
		return usuarioAutor;
	}


	public void validarExpertoVotandoMuestraExpertos() {
		this.estadoAutorAlOpinar.validarExpertoVotandoMuestraExpertos();
	}
	
}
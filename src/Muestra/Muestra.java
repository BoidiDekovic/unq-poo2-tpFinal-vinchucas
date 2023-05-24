package Muestra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Sistema.Sistema;
import Usuario.Usuario;
import ZonaDeCobertura.Ubicacion;

public class Muestra {

	private Sistema sistema;
	private TipoOpinion posibleVinchuca ;
	private String foto;
	private LocalDate fechaCreacion;
	private LocalDate fechaUltimaVotacion ;
	private Ubicacion ubicacion;
	private List<Opinion> opiniones;
	private Usuario usuarioAutor;
	private EstadoMuestra estadoMuestra;
	
	
	
	public Muestra(Sistema sistema, TipoOpinion posibleVinchuca, String foto, LocalDate fechaCreacion,
			LocalDate fechaUltimaVotacion, Ubicacion ubicacion, Usuario usuarioAutor, EstadoMuestra estadoMuestra) {
		super();
		this.sistema = sistema;
		this.posibleVinchuca = posibleVinchuca;
		this.foto = foto;
		this.fechaCreacion = fechaCreacion;
		this.fechaUltimaVotacion = fechaUltimaVotacion;
		this.ubicacion = ubicacion;
		this.usuarioAutor = usuarioAutor;
		this.estadoMuestra = estadoMuestra;
		this.opiniones = new ArrayList<Opinion>();
	}

	public TipoOpinion getPosibleVinchuca() {
		return posibleVinchuca;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDate getFechaUltimaVotacion() {
		return fechaUltimaVotacion;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public Usuario getUsuarioAutor() {
		return usuarioAutor;
	}

	public EstadoMuestra getEstadoMuestra() {
		return estadoMuestra;
	}

	public void agregarOpinion(Opinion opinion) {
		this.estadoMuestra.agregarOpinion(opinion, this);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

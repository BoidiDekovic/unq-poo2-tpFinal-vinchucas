 package Muestra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Sistema.Sistema;
import Usuario.Usuario;
import Usuario.UsuarioExperto;
import ZonaDeCobertura.Ubicacion;

public class Muestra {

	private Sistema sistema;
	private TipoOpinion vinchucaSegunAutor ;
	private String foto;
	private LocalDate fechaCreacion;
	private LocalDate fechaUltimaVotacion ;
	private Ubicacion ubicacion;
	private List<Opinion> opiniones;
	private Usuario usuarioAutor;
	private EstadoMuestra estadoMuestra;
	
	
	
	public Muestra(Sistema sistema, TipoOpinion vinchucaSegunAutor, String foto, Ubicacion ubicacion,
			Usuario usuarioAutor, EstadoMuestra estadoMuestra) {
		super();
		this.sistema = sistema;
		this.vinchucaSegunAutor = vinchucaSegunAutor;
		this.foto = foto;
		this.fechaCreacion = LocalDate.now();
		this.fechaUltimaVotacion = null;
		this.ubicacion = ubicacion;
		this.usuarioAutor = usuarioAutor;
		this.estadoMuestra = estadoMuestra;
		this.opiniones = new ArrayList<Opinion>();
	}

	public TipoOpinion getVinchucaSegunAutor() {
		return vinchucaSegunAutor;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public LocalDate getFechaUltimaVotacion() {
		return this.fechaUltimaVotacion;
	}
	
	
	public void setFechaUltimaVotacion(LocalDate fechaUltimaVotacion) {
		this.fechaUltimaVotacion = fechaUltimaVotacion;
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

	private void setEstadoMuestra(EstadoMuestra estadoMuestra) {
		this.estadoMuestra = estadoMuestra;
	}

	public void agregarOpinion(Opinion opinion) {
		this.estadoMuestra.agregarOpinion(opinion, this);
	}
	
	private void validarOpinionDeDiferenteUsuario(Opinion opinion) throws Exception {
		if (this.usuarioAutor.getOpinionesEnviadas().contains(opinion)) {
			throw new Exception("No se puede opinar sobre la muestra que subiste");
		}
	}
	
	public void agregarOpinionAMuestra(Opinion opinion) {
		this.opiniones.add(opinion);
		this.setFechaUltimaVotacion(opinion.getFechaDeEnvio());
	}

	public TipoOpinion resultadoActual() {
		return this.estadoMuestra.resultadoActual(this);
	}

	public void calcularVerificacion() {
		if (this.resultadoActual() != TipoOpinion.NODEFINIDO && this.esMuestraQueCoincidenDosExpertosEnOpinion()) {
			this.setEstadoMuestra(new MuestraVerificada());
			this.sistema.notificarVerificacionMuestraAZonas(this);
		}
	
	}
	
	private boolean esMuestraQueCoincidenDosExpertosEnOpinion() {
		if (this.opiniones.isEmpty()) {
			return false;
		} else {
			return this.opiniones.stream().filter(o -> o.getEstadoAutor().esExperto())
					  					  .map(o -> o.getTipoOpinion()).collect(Collectors.groupingBy(o -> o, Collectors.counting()))
					  					  .entrySet().stream().anyMatch(to -> to.getValue() == 2);
		}
	}

	public void enviarseASistema() {
		this.sistema.agregarMuestra(this);
		
	}
	
	
	
	
	
	
	
	
	
	
}

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
	
	
	
	public Muestra(TipoOpinion vinchucaSegunAutor, String foto, Ubicacion ubicacion, Usuario usuarioAutor) {
		super();
		this.vinchucaSegunAutor = vinchucaSegunAutor;
		this.foto = foto;
		this.fechaCreacion = LocalDate.now();
		this.fechaUltimaVotacion = null;
		this.ubicacion = ubicacion;
		this.usuarioAutor = usuarioAutor;
		this.estadoMuestra = new MuestraBasicos();
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

	public void setEstadoMuestra(EstadoMuestra estadoMuestra) {
		this.estadoMuestra = estadoMuestra;
	}

	public void agregarOpinion(Opinion opinion) {
		this.validarOpinion(opinion);
		this.estadoMuestra.agregarOpinion(opinion, this);
	}
	
	private void validarOpinion(Opinion opinion) {
		if (opinion.getUsuarioAutor().tieneMuestra(this)) {
			throw new UnsupportedOperationException
			("Un usuario no puede opinar sobre sus propias muestras");
		}
		
		if (this.esMuestraConOpinionDe(opinion.getUsuarioAutor())) {
			throw new UnsupportedOperationException
			("Un usuario no puede opinar dos veces la misma muestra");
		}
	}

	public void agregarOpinionAMuestra(Opinion opinion) {
		this.opiniones.add(opinion);
		this.setFechaUltimaVotacion(opinion.getFechaDeEnvio());
	}

	public TipoOpinion resultadoActual() {
		if(opiniones.isEmpty()) { 
			return this.getVinchucaSegunAutor();
		}
		return this.estadoMuestra.resultadoActual(this);
	}
	
	public void calcularEstadoMuestra() {
		this.estadoMuestra.calcularEstadoMuestra(this);
	}
	
	public boolean esMuestraQueCoincidenDosExpertosEnOpinion() {
		
			return this.opiniones.stream().filter(o -> o.esOpinionDeExperto())
					  					  .map(o -> o.getTipoOpinion()).collect(Collectors.groupingBy(o -> o, Collectors.counting()))
					  					  .entrySet().stream().anyMatch(to -> to.getValue() == 2);
		
	}
	
	// ARREGLAR ESTE METODO
	public void enviarseASistema(Sistema sistema) {
		sistema.agregarMuestra(this);
		this.sistema = sistema;
	}
	
	public boolean esMuestraConOpinionDeExperto() {
		return opiniones.stream().anyMatch(o -> o.esOpinionDeExperto());
	}

	public boolean esMuestraConOpinionDe(Usuario usuario) {
		return opiniones.stream().anyMatch(o -> o.getUsuarioAutor().equals(usuario));
	}
	
	
	
}

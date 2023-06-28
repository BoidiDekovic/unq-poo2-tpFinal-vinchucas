 package Muestra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Sistema.Sistema;
import Usuario.Usuario;
import ZonaDeCobertura.Ubicacion;

public class Muestra {

	private TipoOpinion vinchucaSegunAutor ;
	private String foto;
	private LocalDate fechaCreacion;
	private LocalDate fechaUltimaVotacion ;
	private Ubicacion ubicacion;
	private List<Opinion> opiniones;
	private EstadoMuestra estadoMuestra;
	
	
	
	public Muestra(TipoOpinion vinchucaSegunAutor, String foto, Ubicacion ubicacion) {
		super();
		this.vinchucaSegunAutor = vinchucaSegunAutor;
		this.foto = foto;
		this.fechaCreacion = LocalDate.now();
		this.fechaUltimaVotacion = null;
		this.ubicacion = ubicacion;
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

	public EstadoMuestra getEstadoMuestra() {
		return estadoMuestra;
	}

	public void setEstadoMuestra(EstadoMuestra estadoMuestra) {
		this.estadoMuestra = estadoMuestra;
	}

	public void agregarOpinion(Opinion opinion, Sistema sistema) {
		this.validarOpinion(opinion);
		this.estadoMuestra.agregarOpinion(opinion, this, sistema);
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
	
	public void calcularEstadoMuestra(Sistema sistema) {
		this.estadoMuestra.calcularEstadoMuestra(this, sistema);
	}
	
	public boolean esMuestraQueCoincidenDosExpertosEnOpinion() {
		
			return this.opiniones.stream().filter(o -> o.esOpinionDeExperto())
					  					  .map(o -> o.getTipoOpinion()).collect(Collectors.groupingBy(o -> o, Collectors.counting()))
					  					  .entrySet().stream().anyMatch(to -> to.getValue() == 2);
		
	}
	
	public boolean esMuestraConOpinionDeExperto() {
		return opiniones.stream().anyMatch(o -> o.esOpinionDeExperto());
	}

	public boolean esMuestraConOpinionDe(Usuario usuario) {
		return opiniones.stream().anyMatch(o -> o.getUsuarioAutor().equals(usuario));
	}
	
	
	
}

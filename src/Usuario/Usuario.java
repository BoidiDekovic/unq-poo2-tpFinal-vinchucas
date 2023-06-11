package Usuario;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import Muestra.Muestra;
import Muestra.Opinion;
import Muestra.TipoOpinion;

public class Usuario {
	
	protected EstadoUsuario estado;
	protected List<Muestra> muestras;
	protected List<Opinion> opinionesEnviadas;
	
	public Usuario() {	
		super();
		this.estado = new UsuarioBasico();
		this.muestras = new ArrayList<Muestra>();
		this.opinionesEnviadas = new ArrayList<Opinion>();
	}

	public void agregarYEnviar(Muestra muestra){
		this.estado.agregarYEnviar(muestra, this);
	}
	
	public void opinarSobreMuestra(Muestra muestra, TipoOpinion tipoOpinion) {
		this.validarOpinarSobreUnaMuestra(muestra);
		this.estado.opinarSobreMuestra(muestra, tipoOpinion, this);
	}

	private void validarOpinarSobreUnaMuestra(Muestra muestra) {
		if(this.muestras.contains(muestra)) {
			throw new UnsupportedOperationException("No se puede opinar sobre muestras propias");
		}
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}

	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}
	
	public EstadoUsuario getEstado() {
		return estado;
	}
	
	private List<Muestra> muestrasEnviadasEnLosUltimosTreintaDias() {
		return this.getMuestras().stream()
						.filter(m -> m.getFechaCreacion()
					    .isAfter(LocalDate.now()
					    .minusDays(30))).toList();
	}
	
	private List<Opinion> opinionesEnviadasEnLosUltimosTreintaDias(){
		return this.getOpinionesEnviadas().stream()
				.filter(o -> o.getFechaDeEnvio()
			    .isAfter(LocalDate.now()
			    .minusDays(30))).toList();
				
		
	}
	
	public void agregarMuestra(Muestra muestra) {
		this.muestras.add(muestra);
	}
	
	public List<Opinion> getOpinionesEnviadas() {
		return opinionesEnviadas;
	}
	
	public void calcularCategoria() {
		if(tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias() 
			&& tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias()) {
			this.setEstado(new UsuarioExperto());
		}else {
			this.setEstado(new UsuarioBasico());
		}	
	}

	public boolean tieneMasDeVeinteOpinionesEnviadasEnLosUltimosTreintaDias() {
		return this.opinionesEnviadasEnLosUltimosTreintaDias().size() >20;
	}

	public boolean tieneMasDeDiezMuestrasEnviadasEnLosUltimosTreintaDias() {
		return this.muestrasEnviadasEnLosUltimosTreintaDias().size() > 10;
	}
	
	public void agregarOpinionEnviada(Opinion opinion) {
		this.opinionesEnviadas.add(opinion);
	}

	
}

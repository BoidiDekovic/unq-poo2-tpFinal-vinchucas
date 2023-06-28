package Usuario;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import Muestra.Muestra;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Sistema.Sistema;

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
	
	public void opinarSobreMuestra(Muestra muestra, TipoOpinion tipoOpinion, Sistema sistema) {
		Opinion nuevaOpinion = new Opinion(tipoOpinion, this, LocalDate.now());
		muestra.agregarOpinion(nuevaOpinion, sistema);
		this.agregarOpinionEnviada(nuevaOpinion);
	}
	
	public void agregarYEnviar(Muestra muestra, Sistema sistema) {
		sistema.agregarMuestra(muestra);
		this.agregarMuestra(muestra);
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
		this.estado.calcularCategoria(this);
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

	public boolean tieneMuestra(Muestra muestra) {
		return this.getMuestras().contains(muestra);
	}

}

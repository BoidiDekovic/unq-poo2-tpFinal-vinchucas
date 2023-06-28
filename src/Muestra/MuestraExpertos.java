package Muestra;

import java.util.List;

import Sistema.Sistema;

public class MuestraExpertos extends EstadoMuestra {

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra, Sistema sistema) {
		opinion.validarExpertoVotandoMuestraExpertos();
		muestra.agregarOpinionAMuestra(opinion);
		muestra.calcularEstadoMuestra(sistema);
	}

	@Override
	public void calcularEstadoMuestra(Muestra muestra, Sistema sistema) {
		if (muestra.esMuestraQueCoincidenDosExpertosEnOpinion()) {
			muestra.setEstadoMuestra(new MuestraVerificada(muestra.resultadoActual()));
			sistema.notificarVerificacionMuestraAZonas(muestra);
		}
	}

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		List<Opinion> opiniones = muestra.getOpiniones();
		return resultadoEntre(opiniones.stream().filter(o-> o.esOpinionDeExperto()).toList());
			
		}

}

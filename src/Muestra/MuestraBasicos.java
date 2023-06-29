package Muestra;

import Sistema.Sistema;

public class MuestraBasicos extends EstadoMuestra {
	
	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra, Sistema sistema) {
		muestra.agregarOpinionAMuestra(opinion);
		muestra.calcularEstadoMuestra(sistema);
	}
	
	@Override
	public void calcularEstadoMuestra(Muestra muestra, Sistema sistema) {
		if (muestra.esMuestraConOpinionDeExperto()) {
			muestra.setEstadoMuestra(new MuestraExpertos());
		}
	}

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		return this.resultadoEntre(muestra.getOpiniones());
	}
	
	
}

package Muestra;

public class MuestraExpertos extends EstadoMuestra {

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		this.validarUsuario(opinion, muestra);
		muestra.agregarOpinionAMuestra(opinion);
		muestra.calcularEstadoMuestra();
	}
	
	private void validarUsuario(Opinion opinion, Muestra muestra) {
		if (!opinion.esOpinionDeExperto()) {
			throw new UnsupportedOperationException
			("Un usuario basico no puede opinar una muestra que fue opinada por un experto");
		}
	}

	@Override
	public void calcularEstadoMuestra(Muestra muestra) {
		if (muestra.esMuestraQueCoincidenDosExpertosEnOpinion()) {
			muestra.setEstadoMuestra(new MuestraVerificada());
		}
	}

}

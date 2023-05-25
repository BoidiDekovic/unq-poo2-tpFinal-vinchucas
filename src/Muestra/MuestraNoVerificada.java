package Muestra;

public class MuestraNoVerificada implements EstadoMuestra {

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		muestra.agregarOpinionAMuestraNoVerificada(opinion);
		muestra.calcularVerificacion();
	}

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		return TipoOpinion.VINCHUCAINFESTANS;
	}

}

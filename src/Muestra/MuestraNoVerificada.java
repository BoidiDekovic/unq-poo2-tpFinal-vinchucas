package Muestra;


public class MuestraNoVerificada extends EstadoMuestra {

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		muestra.agregarOpinionAMuestraNoVerificada(opinion);
		muestra.calcularVerificacion();
	}
	
}
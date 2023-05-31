package Muestra;


public class MuestraNoVerificada extends EstadoMuestra {

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		muestra.agregarOpinionAMuestra(opinion);
		muestra.calcularVerificacion();
	}
	
}
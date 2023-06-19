package Muestra;

public class MuestraBasicos extends EstadoMuestra {
	
	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		muestra.agregarOpinionAMuestra(opinion);
		muestra.calcularEstadoMuestra();
	}
	
	@Override
	public void calcularEstadoMuestra(Muestra muestra) {
		if (muestra.esMuestraConOpinionDeExperto()) {
			muestra.setEstadoMuestra(new MuestraExpertos());
		}
	}
	
}

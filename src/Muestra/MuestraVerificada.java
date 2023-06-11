package Muestra;

public class MuestraVerificada extends EstadoMuestra {

	
	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra){
		throw new UnsupportedOperationException("No se pueden agregar opiniones a una muestra verificada");
	}

}

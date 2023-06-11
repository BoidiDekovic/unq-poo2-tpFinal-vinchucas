package Muestra;

public class MuestraVerificada extends EstadoMuestra {

	
	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra) throws Exception {
		throw new Exception("No se puede agregar opinion a una muestra verificada");
	}

}

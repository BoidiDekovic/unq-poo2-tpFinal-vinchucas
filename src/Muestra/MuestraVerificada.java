package Muestra;

import Sistema.Sistema;

public class MuestraVerificada extends EstadoMuestra {
	
	
	private TipoOpinion resultado;
	
	public MuestraVerificada(TipoOpinion resultado) {
		super();
		this.resultado = resultado;
		
	}

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra, Sistema sistema){
		throw new UnsupportedOperationException("No se pueden agregar opiniones a una muestra verificada");
	}

	@Override
	public void calcularEstadoMuestra(Muestra muestra, Sistema sistema) {
		// El estado no cambia porque la muestra ya esta verificada
	}

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		
		return this.resultado;
	}

}

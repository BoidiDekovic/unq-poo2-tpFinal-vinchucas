package Muestra;

public class MuestraVerificada extends EstadoMuestra {
	
	
	private TipoOpinion resultado;
	
	public MuestraVerificada(TipoOpinion resultado) {
		super();
		this.resultado = resultado;
		
	}

	@Override
	public void agregarOpinion(Opinion opinion, Muestra muestra){
		throw new UnsupportedOperationException("No se pueden agregar opiniones a una muestra verificada");
	}

	@Override
	public void calcularEstadoMuestra(Muestra muestra) {
		// El estado no cambia porque la muestra ya esta verificada
	}

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		
		return this.resultado;
	}

}

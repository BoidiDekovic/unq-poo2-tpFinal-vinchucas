package Muestra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

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

	@Override
	public TipoOpinion resultadoActual(Muestra muestra) {
		return this.resultadoEntre(muestra.getOpiniones());
	}
	
	
}

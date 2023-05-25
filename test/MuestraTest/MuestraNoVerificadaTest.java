package MuestraTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.MuestraNoVerificada;
import Muestra.Opinion;
import Muestra.TipoOpinion;

public class MuestraNoVerificadaTest {

	private MuestraNoVerificada estadoMuestraNoVerificada;
	private Muestra muestra;
	private Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		//DOC
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		//SUT
		estadoMuestraNoVerificada = new MuestraNoVerificada();
	}
	
	@Test
	public void testAgregarOpinion() {
		estadoMuestraNoVerificada.agregarOpinion(opinion, muestra);
		verify(muestra, times(1)).agregarOpinionAMuestraNoVerificada(opinion);
		verify(muestra, times(1)).calcularVerificacion();
	}
	
	@Test
	public void testResultadoActualOpinionDelAutor() {
		when(muestra.getVinchucaSegunAutor()).thenReturn(TipoOpinion.VINCHUCAINFESTANS);
		assertEquals(TipoOpinion.VINCHUCAINFESTANS, estadoMuestraNoVerificada.resultadoActual(muestra));
	}
	
	
}

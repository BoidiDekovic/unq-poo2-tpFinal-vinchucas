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
	private Opinion opinion , opinion2, opinion3;
	
	@BeforeEach
	public void setUp() {
		//DOC
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
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

	@Test 
	public void testElResultadoActualCambiaAlagregarPrimeraOpinion() {  
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion));
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCASORDIDA);
		assertEquals( TipoOpinion.VINCHUCASORDIDA , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test 
	public void testElResultadoActualEsElQueTieneMasVotos() {
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAINFESTANS);
		when(opinion3.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2,opinion3));
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones(); 
	}
	
	@Test
	public void testElResultadoActualEnCasoDeEmpateDeVotos(){
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		assertEquals( TipoOpinion.NODEFINIDO , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones(); 
		
	}

	
}

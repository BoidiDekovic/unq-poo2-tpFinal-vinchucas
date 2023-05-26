package MuestraTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;

import Muestra.Muestra;
import Muestra.MuestraNoVerificada;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class MuestraNoVerificadaTest {

	private MuestraNoVerificada estadoMuestraNoVerificada;
	private Muestra muestra;
	private Opinion opinion , opinion2, opinion3;
	private UsuarioExperto estadoUsuarioExperto;
	private UsuarioBasico estadoUsuarioBasico;
	
	@BeforeEach
	public void setUp() {
		//DOC
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		estadoUsuarioBasico = mock(UsuarioBasico.class);
		estadoUsuarioExperto = mock(UsuarioExperto.class);
		when(estadoUsuarioBasico.esExperto()).thenReturn(false);
		when(estadoUsuarioExperto.esExperto()).thenReturn(true);
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
		when(opinion.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		assertEquals( TipoOpinion.VINCHUCASORDIDA , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test 
	public void testElResultadoActualEsElQueTieneMasVotos() {
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAINFESTANS);
		when(opinion3.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		when(opinion2.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		when(opinion3.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2,opinion3));
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones(); 
	}
	
	@Test
	public void testElResultadoActualEnCasoDeEmpateDeVotosEsNoDefinido(){
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		when(opinion2.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		assertEquals( TipoOpinion.NODEFINIDO , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}

	@Test
	public void testCuandoOpinaUnExpertoYUnBasicoElResultadoActualEsElDelExperto(){
		
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.getEstadoAutor()).thenReturn(estadoUsuarioExperto);
		when(opinion2.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test
	public void testCuandoOpinaUnExpertoYDosBasicosElResultadoActualEsElDelExperto(){
		
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion3.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.getEstadoAutor()).thenReturn(estadoUsuarioExperto);
		when(opinion2.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		when(opinion3.getEstadoAutor()).thenReturn(estadoUsuarioBasico);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2, opinion3));
		
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test
	public void testCuandoOpinanDosExpertosDiferenteElResultadoEsNoDefinido(){
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.getEstadoAutor()).thenReturn(estadoUsuarioExperto);
		when(opinion2.getEstadoAutor()).thenReturn(estadoUsuarioExperto);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		
		assertEquals( TipoOpinion.NODEFINIDO , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test
	public void testCuandoOpinaUnExpertoDiferenteYDosExpertosIgualElResultadoActualEsElDeLosDos(){
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion3.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.getEstadoAutor()).thenReturn(estadoUsuarioExperto);
		when(opinion2.getEstadoAutor()).thenReturn(estadoUsuarioExperto);
		when(opinion3.getEstadoAutor()).thenReturn(estadoUsuarioExperto);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2,opinion3));
		
		assertEquals( TipoOpinion.VINCHUCAGUASAYANA , estadoMuestraNoVerificada.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}

	
}

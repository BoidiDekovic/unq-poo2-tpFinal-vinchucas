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

public class EstadoMuestraTest {

	private MuestraNoVerificada estadoMuestra;
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
		estadoMuestra = new MuestraNoVerificada();
	}
	
	@Test
	public void testResultadoActualOpinionDelAutor() {
		when(muestra.getVinchucaSegunAutor()).thenReturn(TipoOpinion.VINCHUCAINFESTANS);
		assertEquals(TipoOpinion.VINCHUCAINFESTANS, estadoMuestra.resultadoActual(muestra));
	}

	@Test 
	public void testElResultadoActualCambiaAlagregarPrimeraOpinion() {  
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion));
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCASORDIDA);
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		assertEquals( TipoOpinion.VINCHUCASORDIDA , estadoMuestra.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test 
	public void testElResultadoActualEsElQueTieneMasVotos() {
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAINFESTANS);
		when(opinion3.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(opinion2.esOpinionDeExperto()).thenReturn(false);
		when(opinion3.esOpinionDeExperto()).thenReturn(false);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2,opinion3));
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestra.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones(); 
	}
	
	@Test
	public void testElResultadoActualEnCasoDeEmpateDeVotosEsNoDefinido(){
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(opinion2.esOpinionDeExperto()).thenReturn(true);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		assertEquals( TipoOpinion.NODEFINIDO , estadoMuestra.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}

	@Test
	public void testCuandoOpinaUnExpertoYUnBasicoElResultadoActualEsElDelExperto(){
		
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(opinion2.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestra.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test
	public void testCuandoOpinaUnExpertoYDosBasicosElResultadoActualEsElDelExperto(){
		
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion3.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(opinion2.esOpinionDeExperto()).thenReturn(false);
		when(opinion3.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2, opinion3));
		
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestra.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test
	public void testCuandoOpinanDosExpertosDiferenteElResultadoEsNoDefinido(){
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(opinion2.esOpinionDeExperto()).thenReturn(true);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		
		assertEquals( TipoOpinion.NODEFINIDO , estadoMuestra.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
	@Test
	public void testCuandoOpinaUnExpertoDiferenteYDosExpertosIgualElResultadoActualEsElDeLosDos(){
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion3.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(opinion2.esOpinionDeExperto()).thenReturn(true);
		when(opinion3.esOpinionDeExperto()).thenReturn(true);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2,opinion3));
		
		assertEquals( TipoOpinion.VINCHUCAGUASAYANA , estadoMuestra.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
}	
	
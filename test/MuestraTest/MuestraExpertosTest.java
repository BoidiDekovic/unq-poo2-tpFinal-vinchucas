package MuestraTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.MuestraExpertos;
import Muestra.MuestraVerificada;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Sistema.Sistema;
import Usuario.Usuario;

class MuestraExpertosTest {
	
	private Sistema sistema;
	private MuestraExpertos estadoMuestraExpertos;
	private Muestra muestra;
	private Opinion opinion,opinion2,opinion3;
	private List<Muestra> muestras;
	private Usuario usuario;
	
	@BeforeEach
	public void setUp() {
		//DOC
		sistema = mock(Sistema.class);
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		usuario = mock(Usuario.class);
		muestras = new ArrayList<>();
		when(usuario.getMuestras()).thenReturn(muestras);
		when(opinion.getUsuarioAutor()).thenReturn(usuario);
		//SUT
		estadoMuestraExpertos = new MuestraExpertos();
	}
	
	@Test 
	public void testCuandoUnaMuestraTieneDosOpinionesDeExpertosQueCoincidenSuEstadoCambiaAMuestraVerificadaYSeLeMandaAlSistema() {
		when(muestra.esMuestraQueCoincidenDosExpertosEnOpinion()).thenReturn(true);
		estadoMuestraExpertos.calcularEstadoMuestra(muestra, sistema);
		verify(muestra,times(1)).setEstadoMuestra(any(MuestraVerificada.class));
		verify(sistema, times(1)).notificarVerificacionMuestraAZonas(muestra);
	}
	
	@Test 
	public void testCuandoUnaMuestraNoTieneDosOpinionesDeExpertosQueCoincidenSuEstadoNoCambia() {
		when(muestra.esMuestraQueCoincidenDosExpertosEnOpinion()).thenReturn(false);
		estadoMuestraExpertos.calcularEstadoMuestra(muestra, sistema);
		verify(muestra,never()).setEstadoMuestra(any());
	}
	
	
	@Test
	public void testSeAgregaOpinionDeUsuarioBasicoEnUnaMuestraConOpinionesDeExperto() {
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		
		Throwable excepcion = new UnsupportedOperationException("Un usuario basico no puede opinar una muestra que fue opinada por un experto");
		doThrow(excepcion).when(opinion).validarExpertoVotandoMuestraExpertos();
		
		assertThrows(UnsupportedOperationException.class, 
				() -> {estadoMuestraExpertos.agregarOpinion(opinion, muestra, sistema);}
				, "Un usuario basico no puede opinar una muestra que fue opinada por un experto");
		
		verify(opinion, times(1)).validarExpertoVotandoMuestraExpertos();
	}
	
	@Test
	public void testSeAgregaOpinionDeUsuarioExpertoEnUnaMuestraConOpinionesDeExperto(){
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		estadoMuestraExpertos.agregarOpinion(opinion, muestra, sistema);
		verify(muestra, times(1)).agregarOpinionAMuestra(opinion);
		verify(muestra, times(1)).calcularEstadoMuestra(sistema);
	}
	@Test
	public void testCuandoOpinaUnExpertoYUnBasicoElResultadoActualEsElDelExperto(){
		
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(opinion2.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestraExpertos.resultadoActual(muestra));
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
		
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestraExpertos.resultadoActual(muestra));
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
		
		assertEquals( TipoOpinion.NODEFINIDO , estadoMuestraExpertos.resultadoActual(muestra));
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
		
		assertEquals( TipoOpinion.VINCHUCAGUASAYANA , estadoMuestraExpertos.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}

}

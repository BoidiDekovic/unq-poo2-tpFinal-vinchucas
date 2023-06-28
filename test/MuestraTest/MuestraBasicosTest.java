package MuestraTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.MuestraBasicos;
import Muestra.MuestraExpertos;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Sistema.Sistema;
import Usuario.Usuario;

public class MuestraBasicosTest {
	
	private Sistema sistema;
	private MuestraBasicos estadoMuestraBasicos;
	private Muestra muestra;
	private Opinion opinion , opinion2,opinion3;
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
		estadoMuestraBasicos = new MuestraBasicos();
	}
	
	@Test
	public void testCuandoUnaMuestraNoTieneOpinionDeExpertosSuEstadoNoCambia() {
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(false);
		estadoMuestraBasicos.calcularEstadoMuestra(muestra, sistema);
		verify(muestra, never()).setEstadoMuestra(any());
	}
	
	@Test
	public void testCuandoUnaMuestraTieneOpinionDeExpertosSuEstadoCambiaAEstadoExperto() {
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		estadoMuestraBasicos.calcularEstadoMuestra(muestra, sistema);
		verify(muestra, times(1)).setEstadoMuestra(any(MuestraExpertos.class));
	}
	
	@Test
	public void testSeAgregaOpinionDeUsuarioBasicoEnUnaMuestraSinOpinionesDeExperto() {
		Sistema sistema = mock(Sistema.class);
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(false);
		
		estadoMuestraBasicos.agregarOpinion(opinion, muestra, sistema);
		
		verify(muestra, times(1)).agregarOpinionAMuestra(opinion);
		verify(muestra, times(1)).calcularEstadoMuestra(sistema);
	}

	@Test 
	public void testElResultadoActualCambiaAlagregarPrimeraOpinion() {  
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion));
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCASORDIDA);
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		assertEquals( TipoOpinion.VINCHUCASORDIDA , estadoMuestraBasicos.resultadoActual(muestra));
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
		assertEquals( TipoOpinion.CHINCHEFOLIADA , estadoMuestraBasicos.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones(); 
	}
	
	@Test
	public void testElResultadoActualEnCasoDeEmpateDeVotosEsNoDefinido(){
		when(opinion.getTipoOpinion()).thenReturn(TipoOpinion.CHINCHEFOLIADA);
		when(opinion2.getTipoOpinion()).thenReturn(TipoOpinion.VINCHUCAGUASAYANA);
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(opinion2.esOpinionDeExperto()).thenReturn(true);
		when(muestra.getOpiniones()).thenReturn(Arrays.asList(opinion,opinion2));
		assertEquals( TipoOpinion.NODEFINIDO , estadoMuestraBasicos.resultadoActual(muestra));
		verify(muestra, atLeast(1)).getOpiniones();
	}
	
}

package MuestraTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Muestra.Muestra;
import Muestra.MuestraBasicos;
import Muestra.MuestraExpertos;
import Muestra.MuestraVerificada;
import Muestra.Opinion;
import Usuario.Usuario;

class MuestraExpertosTest {

	private MuestraExpertos estadoMuestraExpertos;
	private Muestra muestra;
	private Opinion opinion;
	private List<Muestra> muestras;
	private Usuario usuario;
	
	@BeforeEach
	public void setUp() {
		//DOC
		muestra = mock(Muestra.class);
		opinion = mock(Opinion.class);
		usuario = mock(Usuario.class);
		muestras = new ArrayList<>();
		when(usuario.getMuestras()).thenReturn(muestras);
		when(opinion.getUsuarioAutor()).thenReturn(usuario);
		//SUT
		estadoMuestraExpertos = new MuestraExpertos();
	}
	
	@Test 
	public void testCuandoUnaMuestraTieneDosOpinionesDeExpertosQueCoincidenSuEstadoCambiaAMuestraVerificada() {
		when(muestra.esMuestraQueCoincidenDosExpertosEnOpinion()).thenReturn(true);
		estadoMuestraExpertos.calcularEstadoMuestra(muestra);
		verify(muestra,times(1)).setEstadoMuestra(any(MuestraVerificada.class));
	}
	@Test 
	public void testCuandoUnaMuestraNoTieneDosOpinionesDeExpertosQueCoincidenSuEstadoNoCambia() {
		when(muestra.esMuestraQueCoincidenDosExpertosEnOpinion()).thenReturn(false);
		estadoMuestraExpertos.calcularEstadoMuestra(muestra);
		verify(muestra,never()).setEstadoMuestra(any());
	}
	
	
	@Test
	public void testSeAgregaOpinionDeUsuarioBasicoEnUnaMuestraConOpinionesDeExperto() {
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		
		assertThrows(UnsupportedOperationException.class, 
				() -> {estadoMuestraExpertos.agregarOpinion(opinion, muestra);}
				, "Un usuario basico no puede opinar una muestra que fue opinada por un experto");
	}
	
	@Test
	public void testSeAgregaOpinionDeUsuarioExpertoEnUnaMuestraConOpinionesDeExperto(){
		when(opinion.esOpinionDeExperto()).thenReturn(true);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		estadoMuestraExpertos.agregarOpinion(opinion, muestra);
		verify(muestra, times(1)).agregarOpinionAMuestra(opinion);
		verify(muestra, times(1)).calcularEstadoMuestra();
	}
	
	
	

}

package MuestraTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;

import Muestra.Muestra;
import Muestra.MuestraBasicos;
import Muestra.MuestraExpertos;
import Muestra.Opinion;
import Muestra.TipoOpinion;
import Usuario.Usuario;
import Usuario.UsuarioBasico;
import Usuario.UsuarioExperto;

public class MuestraBasicosTest {

	private MuestraBasicos estadoMuestraBasicos;
	private Muestra muestra;
	private Opinion opinion;
	private List<Muestra> muestras;
	private Usuario usuario;
	private List<Opinion> opiniones;
	
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
		estadoMuestraBasicos = new MuestraBasicos();
	}
	
	@Test
	public void testCuandoUnaMuestraNoTieneOpinionDeExpertosSuEstadoNoCambia() {
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(false);
		estadoMuestraBasicos.calcularEstadoMuestra(muestra);
		verify(muestra, never()).setEstadoMuestra(any());
	}
	@Test
	public void testCuandoUnaMuestraTieneOpinionDeExpertosSuEstadoCambiaAEstadoExperto() {
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(true);
		estadoMuestraBasicos.calcularEstadoMuestra(muestra);
		verify(muestra, times(1)).setEstadoMuestra(any(MuestraExpertos.class));
	}
	
	
	
	
	@Test
	public void testSeAgregaOpinionDeUsuarioBasicoEnUnaMuestraSinOpinionesDeExperto() {
		when(opinion.esOpinionDeExperto()).thenReturn(false);
		when(muestra.esMuestraConOpinionDeExperto()).thenReturn(false);
		estadoMuestraBasicos.agregarOpinion(opinion, muestra);
		verify(muestra, times(1)).agregarOpinionAMuestra(opinion);
		verify(muestra, times(1)).calcularEstadoMuestra();
	}

}
